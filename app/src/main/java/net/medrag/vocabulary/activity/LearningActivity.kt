package net.medrag.vocabulary.activity

import android.annotation.SuppressLint
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

    private lateinit var database: Repository
    private lateinit var voc: List<Pair>
    private var iterator = 0
    private var sum = 0
    private var m = 0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_learning)
        database = Repository(this)
        voc = database.extractAll().shuffled()

        tiEdit.setOnEditorActionListener { _, a, _ ->
            if (a == EditorInfo.IME_ACTION_DONE) {
                check.performClick()
                return@setOnEditorActionListener true
            } else return@setOnEditorActionListener false
        }

        tiEdit.setOnFocusChangeListener { _, focus ->
            if (focus) tiEdit.hint = voc[iterator].word else tiEdit.hint = ""
            return@setOnFocusChangeListener
        }

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
            makeText.setGravity(Gravity.TOP, 0, percentage.bottom + 200)
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

    override fun onSaveInstanceState(outState: Bundle) {
        println(voc)
        outState.putParcelableArrayList("voc", voc as ArrayList<out Parcelable>)
        outState.putString("words", words.text.toString())
        outState.putString("percentage", percentage.text.toString())
        outState.putString("answer", tiEdit.text.toString())
        outState.putInt("iterator", iterator)
        outState.putInt("sum", sum)
        outState.putInt("mistakes", m)
        super.onSaveInstanceState(outState)
    }

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
        println(voc)
    }

}