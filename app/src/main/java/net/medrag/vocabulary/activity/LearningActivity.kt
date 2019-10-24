package net.medrag.vocabulary.activity

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_learning.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair
import net.medrag.vocabulary.db.Repository

class LearningActivity : AppCompatActivity() {

    private lateinit var database: Repository
    private lateinit var voc: List<Pair>
    private var iterator = 0;
    private var sum = 0;
    private var m = 0;

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        database = Repository(this)
        voc = database.extractAll().shuffled()

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

    @SuppressLint("SetTextI18n")
    fun checkWord(@Suppress("UNUSED_PARAMETER") view: View) {
        if (!tiEdit.text.toString().equals(voc[iterator++].trans)) {
            AlertDialog.Builder(this)
                .setTitle("Wrong!")
                .setMessage("${voc[iterator - 1].word}\n\n${voc[iterator - 1].trans}")
                .create()
                .show()
            m++
            mistakes.text = m.toString()
        } else {
            val makeText = Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT)
            makeText.setGravity(Gravity.TOP, 0, 475)
            makeText.show()
        }
        if (iterator == voc.size) {
            voc = database.extractAll().shuffled()
            iterator = 0
            sum += voc.size
        }
        words.text = "${iterator + sum}/${voc.size + sum}"
        percentage.text = "${100 - m * 100 / (iterator + sum)}%"
        word.text = voc[iterator].word
        tiEdit.text?.clear()
    }

//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putParcelableArrayList("voc", voc)
//        super.onSaveInstanceState(outState)
//    }
//
//    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
//        super.onRestoreInstanceState(savedInstanceState)
//        savedInstanceState?.getString("x")
//    }

}