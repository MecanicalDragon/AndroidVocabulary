package net.medrag.vocabulary.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_main.wordInput
import kotlinx.android.synthetic.main.find_word_input_part.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair
import net.medrag.vocabulary.db.Repository
import net.medrag.vocabulary.model.FindWordModel


class FindWordForUpdateActivity : AppCompatActivity() {

    private lateinit var database: Repository
    private val model: FindWordModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_word_for_update)
        database = Repository(this)

        model.pattern.observe(this, Observer { wordInput.setText(it) })
        model.wordsTotal.observe(this, Observer { wordsTotal.text = it })
        model.pair.observe(this,
            Observer { wordText.text = it.word; translationText.text = it.trans })

        /**
         * Submit on enter
         */
        wordInput.setOnEditorActionListener { _, a, _ ->
            if (a == EditorInfo.IME_ACTION_DONE) {
                wordInput.clearFocus()
                val layout = findViewById<View>(R.id.findWordWrapperLayout)
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(layout.windowToken, 0)
                findNext(findViewById(R.id.findNext))
                return@setOnEditorActionListener true
            } else return@setOnEditorActionListener false
        }
    }

    /**
     * Show next pair, matching specified pattern.
     */
    fun findNext(@Suppress("UNUSED_PARAMETER") view: View) {
        val iter = model.iter.value ?: 0
        var words = model.words.value ?: ArrayList()
        val pattern = wordInput.text.toString()

        if (words.size <= iter || words.isEmpty() || pattern != model.pattern.value) {
            words = database.getAllLike(pattern)
            if (words.isEmpty()) {
                model.wordsTotal.value = ""
                model.pair.value = Pair.EMPTY_PAIR
                toast(resources.getString(R.string.noWords))
                return
            } else {
                model.setNewWordList(words, pattern)
            }
        }
        model.switchToNextPair()
    }

    /**
     * Goto UpdateActivity to update a word pair.
     */
    fun updateWord(@Suppress("UNUSED_PARAMETER") view: View) {
        if (model.pair.value == Pair.EMPTY_PAIR) {
            toast(resources.getString(R.string.noUpd))
            return
        }
        Intent(this, UpdateActivity::class.java).apply {
            putExtra(resources.getString(R.string.pairExtra), model.pair.value)
            startActivityForResult(this, UPDATE_CODE)
        }
    }

    /**
     * UpdateActivity Result.
     */
    override fun onActivityResult(identifier: Int, result: Int, intent: Intent?) {
        super.onActivityResult(identifier, result, intent)
        if (identifier == UPDATE_CODE && result == Activity.RESULT_OK) {
            toast(resources.getString(R.string.updSuccess))
        } else {
            toast(resources.getString(R.string.error))
        }
        findNext(findViewById(R.id.findNext))
    }

    /**
     * Show the toast.
     */
    private fun toast(msg: String) {
        val a = findViewById<View>(R.id.findWordControlPart).top
        val b = findViewById<View>(R.id.findNext).bottom
        val offset = a + b
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.TOP, 0, offset + TOAST_OFFSET)
            show()
        }
    }

    companion object {
        private const val UPDATE_CODE = 0
        private const val TOAST_OFFSET = 70
    }
}
