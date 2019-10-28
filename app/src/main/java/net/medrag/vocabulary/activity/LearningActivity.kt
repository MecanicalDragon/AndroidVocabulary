package net.medrag.vocabulary.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_learning.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair
import net.medrag.vocabulary.db.Repository
import java.util.ArrayList

class LearningActivity : AppCompatActivity() {

    private val UPDATE_CODE = 0
    private val TOAST_OFFSET = 170
    private lateinit var database: Repository
    private lateinit var voc: List<Pair>
    private var iterator = 0
    private var sum = 0
    private var m = 0

    /**
     * Create new activity
     */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        database = Repository(this)
        voc = database.extractAll().shuffled()

        /**
         * Submit on enter
         */
        tiEdit.setOnEditorActionListener { _, a, _ ->
            if (a == EditorInfo.IME_ACTION_DONE) {
                check.performClick()
                return@setOnEditorActionListener true
            } else return@setOnEditorActionListener false
        }

        /**
         * Useless here
         */
//        tiEdit.setOnFocusChangeListener { _, focus ->
//            if (focus) tiEdit.hint = voc[iterator].word else tiEdit.hint = ""
//            return@setOnFocusChangeListener
//        }

        /**
         * Init text views
         */
        if (voc.isNotEmpty()) {
            word.text = voc[iterator].word
            words.text = "$iterator/${voc.size}"
            mistakes.text = m.toString()
        } else {
            word.text = "Your vocabulary is empty!"
            tiEdit.visibility = View.INVISIBLE
            transInput.visibility = View.INVISIBLE
            check.visibility = View.INVISIBLE
        }
    }

    /**
     * onClick "Check word" button
     */
    @SuppressLint("SetTextI18n")
    fun checkWord(@Suppress("UNUSED_PARAMETER") view: View) {

        /**
         * If answer is incorrect
         */
        if (!tiEdit.text.toString().equals(voc[iterator++].trans)) {
            AlertDialog.Builder(this)
                .setTitle("Wrong!")
                .setMessage("${voc[iterator - 1].word}\n\n${voc[iterator - 1].trans}")
                .create()
                .show()
            m++
            mistakes.text = m.toString()

            /**
             * If answer is correct
             */
        } else {
            val makeText = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT)
            makeText.setGravity(Gravity.TOP, 0, percentage.bottom + TOAST_OFFSET)
            makeText.show()
        }

        /**
         * Get new vocabulary if previous one is over
         */
        if (iterator == voc.size) {
            voc = database.extractAll().shuffled()
            iterator = 0
            sum += voc.size
        }

        /**
         * Fill text views
         */
        words.text = "${iterator + sum}/${voc.size + sum}"
        percentage.text = "${100 - m * 100 / (iterator + sum)}%"
        word.text = voc[iterator].word
        tiEdit.text?.clear()
    }

    /**
     * Update previous pair or toast "Unable" if iterator == 0
     */
    fun updatePair(@Suppress("UNUSED_PARAMETER") view: View) {
        if (iterator == 0) {
            val makeText = Toast.makeText(this, "Unable now.", Toast.LENGTH_SHORT)
            makeText.setGravity(Gravity.TOP, 0, percentage.bottom + TOAST_OFFSET)
            makeText.show()
        } else {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("pair", voc[iterator - 1])
            startActivityForResult(intent, UPDATE_CODE)
        }
    }

    /**
     * Activity Result
     */
    override fun onActivityResult(identifier: Int, result: Int, intent: Intent?) {
        super.onActivityResult(identifier, result, intent)
        if (identifier == UPDATE_CODE && result == Activity.RESULT_OK) {
            val makeText = Toast.makeText(this, "Updated successfully.", Toast.LENGTH_SHORT)
            makeText.setGravity(Gravity.TOP, 0, percentage.bottom + TOAST_OFFSET)
            makeText.show()
        }
    }

    /**
     * Save instance state
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelableArrayList("voc", voc as ArrayList<out Parcelable>)
        outState.putString("words", words.text.toString())
        outState.putString("percentage", percentage.text.toString())
        outState.putString("answer", tiEdit.text.toString())
        outState.putInt("iterator", iterator)
        outState.putInt("sum", sum)
        outState.putInt("mistakes", m)
        super.onSaveInstanceState(outState)
    }

    /**
     * Restore instance state
     */
    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        voc = savedInstanceState?.getParcelableArrayList<Pair>("voc") as List<Pair>
        words.text = savedInstanceState.getString("words")
        percentage.text = savedInstanceState.getString("percentage")
        tiEdit.setText(savedInstanceState.getString("answer"))
        m = savedInstanceState.getInt("mistakes")
        mistakes.text = m.toString()
        iterator = savedInstanceState.getInt("iterator")
        sum = savedInstanceState.getInt("sum")
        word.text = voc[iterator].word
    }

}