package net.medrag.vocabulary.activity

import android.content.ClipDescription.MIMETYPE_TEXT_HTML
import android.content.ClipDescription.MIMETYPE_TEXT_PLAIN
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.text.method.LinkMovementMethod
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.activity_main.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.ReducedPair
import net.medrag.vocabulary.db.Repository
import net.medrag.vocabulary.model.MainModel
import net.medrag.vocabulary.model.MainModel.Companion.MAIN_ACTIVITY
import net.medrag.vocabulary.model.MainModel.Companion.Translation.EN
import net.medrag.vocabulary.model.MainModel.Companion.Translation.RU
import java.io.File
import java.nio.file.Files


class MainActivity : AppCompatActivity() {

    private lateinit var database: Repository
    private val model: MainModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if ((intent?.extras?.get(resources.getString(R.string.closeImmediately)) ?: false) == true) {
            finish()
        }
        database = Repository(this)
        yandexLink.movementMethod = LinkMovementMethod.getInstance()
        model.word.observe(this, Observer { wordInput.setText(it) })
        model.translation.observe(this, Observer { translationInput.setText(it) })
        model.yandexVisibility.observe(this, Observer {
            yandexLicenseText.visibility = if (it) View.VISIBLE else View.GONE
            yandexLink.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    fun addToVoc(@Suppress("UNUSED_PARAMETER") view: View) {
        model.word.value = wordInput.text.toString()
        model.translation.value = translationInput.text.toString()
        if (model.word.value.isNullOrBlank() || model.translation.value.isNullOrBlank()) {
            Toast.makeText(this, "Fill the fields!", Toast.LENGTH_SHORT).show()
        } else {
            if (database.addWord(wordInput.text.toString(), translationInput.text.toString()) > 0) {
                model.word.value = ""
                model.translation.value = ""
                model.yandexVisibility.value = false
                Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun askYandex(@Suppress("UNUSED_PARAMETER") view: View) {
        val word = wordInput.text.toString()
        val translation = translationInput.text.toString()
        if (word.isBlank() && translation.isBlank()) {
            showToast("Nothing to ask.")
            return
        }
        val text = if (word.isNotBlank()) word else translation
        val mode = if (word.isNotBlank()) EN else RU
        yandexRequest(text, mode)
    }

    fun toAchievements(@Suppress("UNUSED_PARAMETER") view: View) =
        startActivity(Intent(this, AchievementsActivity::class.java))

    fun update(@Suppress("UNUSED_PARAMETER") view: View) =
        startActivity(Intent(this, FindWordForUpdateActivity::class.java))

    fun dump(@Suppress("UNUSED_PARAMETER") view: View) {
        val dump: List<String> =
            database.extractAll().map { ReducedPair(it.trans, it.word, it.streak) }
                .map { it.toString() }
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

    fun toNotificationSettings(@Suppress("UNUSED_PARAMETER") view: View) =
        startActivity(Intent(this, NotificationSchedulingActivity::class.java))

    fun learn(@Suppress("UNUSED_PARAMETER") view: View) {
        model.word.value = ""
        model.translation.value = ""
        model.yandexVisibility.value = false
        translationInput.clearFocus()
        wordInput.clearFocus()
        startActivity(Intent(this, GetLearningActivity::class.java))
    }

    fun pasteFromClipboardEn(@Suppress("UNUSED_PARAMETER") view: View) =
        pasteFromClipboard(wordInput, EN)

    fun pasteFromClipboardRu(@Suppress("UNUSED_PARAMETER") view: View) =
        pasteFromClipboard(translationInput, RU)

    private fun pasteFromClipboard(
        field: TextInputEditText,
        mode: MainModel.Companion.Translation
    ) {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        if (clipboard.hasPrimaryClip()) {
            val clip = clipboard.primaryClipDescription
            if (clip?.hasMimeType(MIMETYPE_TEXT_PLAIN) == true
                || clip?.hasMimeType(MIMETYPE_TEXT_HTML) == true
            ) {
                val text = clipboard.primaryClip?.getItemAt(0)?.text
                if (text.isNullOrBlank()) {
                    showToast("Text in clipboard is blank!")
                } else {
                    field.setText(text)
                    yandexRequest(text.toString(), mode)
                }
            } else showToast("Not a text in the clipboard!")
        } else showToast("The clipboard is empty!")
    }

    private fun yandexRequest(textOut: String, mode: MainModel.Companion.Translation) {
        if (textOut.isBlank()) {
            Log.e(MAIN_ACTIVITY, "Passed text is blank. There will be no request to Yandex.")
        } else model.doYandexRequest(textOut, mode, this)
    }

    private fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.TOP, 0, dumpDb.top - 170)
        show()
    }
}
