package net.medrag.vocabulary.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_learning.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Repository
import net.medrag.vocabulary.model.LearningModel

class LearningActivity : AppCompatActivity() {

    private lateinit var database: Repository
    private val model: LearningModel by viewModels()

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

        // Retrieve vocabulary with specified size
        if (model.voc.isEmpty()) {
            val amount = intent?.extras?.getInt(resources.getString(R.string.pickAmount)) ?: 10
            model.voc = database.getWorstLearnedPairs(amount)
        }
        if (model.voc.isNotEmpty()) {
            word.text = model.getTranslation()
            words.text = model.getWordsAmount()
            mistakes.text = model.mistakesCounter.toString()
        } else {
            word.text = "Your vocabulary is empty!"
            tiEdit.visibility = View.INVISIBLE
            transInput.visibility = View.INVISIBLE
            check.visibility = View.INVISIBLE
        }
    }

    override fun onResume() {
        super.onResume()
        if (model.iterator != 0 && model.voc.size == model.iterator) {
            (check as Button).text = "Finish"
            check.setOnClickListener {
                finish()
            }
        }
    }

    /**
     * onClick "Check word" button
     */
    @SuppressLint("SetTextI18n")
    fun checkWord(@Suppress("UNUSED_PARAMETER") view: View) {

        val voc = model.voc
        var iterator = model.iterator

        if (tiEdit.text.toString() != voc[iterator].word) {
            AlertDialog.Builder(this)
                .setTitle("Wrong!")
                .setMessage("${voc[iterator].word}\n\n${voc[iterator].trans}")
                .create()
                .show()
            model.mistakesCounter++
            mistakes.text = model.mistakesCounter.toString()
        } else {
            voc[iterator].learned = true
            val makeText = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT)
            makeText.setGravity(Gravity.TOP, 0, percentage.bottom + TOAST_OFFSET)
            makeText.show()
        }

        iterator = ++model.iterator
        if (iterator == voc.size) {
            val learned = voc.filter { it.learned }.toList()
            database.updateStreak(learned)
            word.text = ""
            (check as Button).text = "Finish"
            check.setOnClickListener {
                finish()
            }
            if (learned.size == voc.size) {
                database.increaseAchievementScore("get${voc.size}GoldenCup")
                startActivity(Intent(this, AchievementsActivity::class.java))
                newAchievement()
            } else if (learned.size > (voc.size * 85 / 100)) {
                database.increaseAchievementScore("get${voc.size}SilverCup")
                startActivity(Intent(this, AchievementsActivity::class.java))
                newAchievement()
            } else if (learned.size > (voc.size * 70 / 100)) {
                database.increaseAchievementScore("get${voc.size}BronzeCup")
                startActivity(Intent(this, AchievementsActivity::class.java))
                newAchievement()
            }
        } else {
            word.text = voc[iterator].trans
        }

        /**
         * Fill text views
         */
        words.text = "${iterator}/${voc.size}"
        percentage.text = "${100 - model.mistakesCounter * 100 / iterator}%"
        tiEdit.text?.clear()
    }

    private fun newAchievement() {
        Toast.makeText(this, "You've unlocked new achievement!", Toast.LENGTH_LONG).apply {
            setGravity(Gravity.TOP, 0, percentage.bottom + TOAST_OFFSET)
            show()
        }
    }

    /**
     * Update previous pair or toast "Unable" if iterator == 0
     */
    fun updatePair(@Suppress("UNUSED_PARAMETER") view: View) {
        if (model.iterator == 0) {
            val makeText = Toast.makeText(this, "Unable now.", Toast.LENGTH_SHORT)
            makeText.setGravity(Gravity.TOP, 0, percentage.bottom + TOAST_OFFSET)
            makeText.show()
        } else {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra(resources.getString(R.string.pairExtra), model.voc[model.iterator - 1])
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

    companion object {
        private const val UPDATE_CODE = 0
        private const val TOAST_OFFSET = 170
    }
}