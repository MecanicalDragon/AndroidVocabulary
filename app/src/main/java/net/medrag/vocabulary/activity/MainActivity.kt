package net.medrag.vocabulary.activity

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.ReducedPair
import net.medrag.vocabulary.db.Repository
import java.io.File
import java.nio.file.Files

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

    fun dump(@Suppress("UNUSED_PARAMETER") view: View) {
        val dump: List<String> =
            database.extractAll().map { ReducedPair(it.trans, it.word) }.map { it.toString() }
                .toList()
        // Check if env is accessible
        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            val externalFilesDir = getExternalFilesDir(null)
            val file = File(externalFilesDir, "DUMP_BD_FILE.TXT")
            if (file.exists()) Files.delete(file.toPath())
            Files.createFile(file.toPath())
            Files.write(file.toPath(), dump)

            val makeText = Toast.makeText(this, "Database has been dumped!", Toast.LENGTH_SHORT)
            makeText.setGravity(Gravity.TOP, 0, dumpDb.top - 170)
            makeText.show()
        }
    }

    fun toNotificationSettings(@Suppress("UNUSED_PARAMETER") view: View) =
        startActivity(Intent(this, NotificationActivity::class.java))

    fun learn(@Suppress("UNUSED_PARAMETER") view: View) {
        wilEdit.text?.clear()
        tilEdit.text?.clear()
        tilEdit.clearFocus()
        wilEdit.clearFocus()
        startActivity(Intent(this, LearningActivity::class.java))
    }
}
