package net.medrag.vocabulary.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Repository

class MainActivity : AppCompatActivity() {

    private lateinit var database: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Repository(this)
    }

    fun addToVoc(@Suppress("UNUSED_PARAMETER") view: View) {

        val word = wilEdit.text.toString()
        val translation = tilEdit.text.toString()
        if (word.isBlank() || translation.isBlank()) {
            Toast.makeText(this, "Fill the fields!", Toast.LENGTH_SHORT).show()
        } else if (database.addWord(wilEdit.text.toString(), tilEdit.text.toString()) > 0) {
            wilEdit.text?.clear()
            tilEdit.text?.clear()
            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
        }
    }

    fun learn(@Suppress("UNUSED_PARAMETER") view: View) {
        wilEdit.text?.clear()
        tilEdit.text?.clear()
        tilEdit.clearFocus()
        wilEdit.clearFocus()
        startActivity(Intent(this, LearningActivity::class.java))
    }
}
