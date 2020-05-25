package net.medrag.vocabulary.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_learning.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair
import net.medrag.vocabulary.db.Repository
import java.util.ArrayList

//TODO: fix this shit
class LearningActivity : AppCompatActivity() {

    private lateinit var database: Repository
    private lateinit var voc: List<Pair>
    private var iterator = 0
    private var mistakesCounter = 0
    private var challenged = false

    /**
     * Create new activity
     */
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        database = Repository(this)

        /**
         * Submit on enter
         */
        tiEdit.setOnEditorActionListener { _, a, _ ->
            if (a == EditorInfo.IME_ACTION_DONE) {
                check.performClick()
                return@setOnEditorActionListener true
            } else return@setOnEditorActionListener false
        }
    }

    override fun onResume() {
        super.onResume()
        if (challenged) {
            getLayout.visibility = View.GONE
            if (iterator != 0 && voc.size == iterator) {
                (check as Button).text = "Finish"
                check.setOnClickListener {
                    learningView.visibility = View.GONE
                    challenged = false
                    tiEdit.text?.clear()
                    getLayout.visibility = View.VISIBLE
                }
            }
            learningView.visibility = View.VISIBLE
        } else {
            learningView.visibility = View.GONE
            getLayout.visibility = View.VISIBLE
        }
    }

    /**
     * onClick "Check word" button
     */
    @SuppressLint("SetTextI18n")
    fun checkWord(@Suppress("UNUSED_PARAMETER") view: View) {

        if (tiEdit.text.toString() != voc[iterator].word) {
            AlertDialog.Builder(this)
                .setTitle("Wrong!")
                .setMessage("${voc[iterator].word}\n\n${voc[iterator].trans}")
                .create()
                .show()
            mistakesCounter++
            mistakes.text = mistakesCounter.toString()
        } else {
            voc[iterator].learned = true
            val makeText = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT)
            makeText.setGravity(Gravity.TOP, 0, percentage.bottom + TOAST_OFFSET)
            makeText.show()
        }

        if (++iterator == voc.size) {
            val learned = voc.filter { it.learned }.toList()
            database.updateStreak(learned)
            AlertDialog.Builder(this)
                .setTitle("Congratulations!")
                .setMessage(
                    "You've finished the challenge, translating correctly ${learned.size} words of ${voc.size}. " +
                            "In later versions you'll receive a medal for this achievement, but for now just take our endorsement."
                )
                .create()
                .show()
            word.text = "Congratulations!"
            (check as Button).text = "Finish"
            check.setOnClickListener {
                learningView.visibility = View.GONE
                tiEdit.text?.clear()
                getLayout.visibility = View.VISIBLE
            }
        } else {
            word.text = voc[iterator].trans
        }

        /**
         * Fill text views
         */
        words.text = "${iterator}/${voc.size}"
        percentage.text = "${100 - mistakesCounter * 100 / iterator}%"
        tiEdit.text?.clear()
    }

    fun startChallenge(view: View) {
        val amount = when ((view as Button).text) {
            resources.getString(R.string.Get10) -> 10
            resources.getString(R.string.Get25) -> 25
            resources.getString(R.string.Get50) -> 50
            resources.getString(R.string.Get100) -> 100
            else -> 10
        }
        getLayout.visibility = View.GONE
        voc = database.getWorstLearnedPairs(amount)
        iterator = 0
        mistakesCounter = 0
        if (voc.isNotEmpty()) {
            word.text = voc[iterator].trans
            words.text = "$iterator/${voc.size}"
            mistakes.text = mistakesCounter.toString()
        } else {
            word.text = "Your vocabulary is empty!"
            tiEdit.visibility = View.INVISIBLE
            transInput.visibility = View.INVISIBLE
            check.visibility = View.INVISIBLE
        }
        challenged = true
        (check as Button).text = "Check"
        check.setOnClickListener { checkWord(it) }
        learningView.visibility = View.VISIBLE
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
        outState.putInt("mistakes", mistakesCounter)
        outState.putBoolean("challenged", challenged)
        outState.putString("currentWord", word.text.toString())
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
        mistakesCounter = savedInstanceState.getInt("mistakes")
        challenged = savedInstanceState.getBoolean("challenged")
        mistakes.text = mistakesCounter.toString()
        iterator = savedInstanceState.getInt("iterator")
        word.text = savedInstanceState.getString("currentWord")
    }

    companion object {
        private const val UPDATE_CODE = 0
        private const val TOAST_OFFSET = 170
    }
}