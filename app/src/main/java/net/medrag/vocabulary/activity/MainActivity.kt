package net.medrag.vocabulary.activity

import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
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

    fun pasteFromClipboardEn(@Suppress("UNUSED_PARAMETER") view: View) = pasteFromClipboard(wilEdit)
    fun pasteFromClipboardRu(@Suppress("UNUSED_PARAMETER") view: View) = pasteFromClipboard(tilEdit)

    private fun pasteFromClipboard(inputField: TextInputEditText) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboard.hasPrimaryClip()) {
            val clip = clipboard.primaryClipDescription
            if (clip?.hasMimeType(MIMETYPE_TEXT_PLAIN) == true) {
                inputField.setText(clipboard.primaryClip?.getItemAt(0)?.text)
            } else showToast("Not a text in the clipboard!")
        } else showToast("The clipboard is empty!")
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
            showToast("Database has been dumped!")
        }
    }

    private fun showToast(msg: String) =
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).apply {
            setGravity(Gravity.TOP, 0, dumpDb.top - 170)
            show()
        }

    fun toNotificationSettings(@Suppress("UNUSED_PARAMETER") view: View) =
        startActivity(Intent(this, NotificationSchedulingActivity::class.java))

    fun learn(@Suppress("UNUSED_PARAMETER") view: View) {
        wilEdit.text?.clear()
        tilEdit.text?.clear()
        tilEdit.clearFocus()
        wilEdit.clearFocus()
        startActivity(Intent(this, LearningActivity::class.java))
    }
}
