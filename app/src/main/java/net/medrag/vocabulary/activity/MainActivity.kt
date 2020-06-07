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
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.ReducedPair
import net.medrag.vocabulary.db.Repository
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.nio.file.Files


class MainActivity : AppCompatActivity() {

    private lateinit var database: Repository
    private lateinit var key: String
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        database = Repository(this)
        key = resources.getString(R.string.yandexApiKey)
        url = resources.getString(R.string.yandexApiRequestUri)
        yandexLink.movementMethod = LinkMovementMethod.getInstance()
        switchYandexVisibility(false)
    }

    fun addToVoc(@Suppress("UNUSED_PARAMETER") view: View) {

        val word = wilEdit.text.toString()
        val translation = tilEdit.text.toString()
        if (word.isBlank() || translation.isBlank()) {
            Toast.makeText(this, "Fill the fields!", Toast.LENGTH_SHORT).show()
        } else if (database.addWord(wilEdit.text.toString(), tilEdit.text.toString()) > 0) {
            wilEdit.text?.clear()
            tilEdit.text?.clear()
            switchYandexVisibility(false)
            Toast.makeText(this, "Successfully added", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Failed to add", Toast.LENGTH_SHORT).show()
        }
    }

    fun askYandex(@Suppress("UNUSED_PARAMETER") view: View) {
        val word = wilEdit.text.toString()
        val translation = tilEdit.text.toString()
        if (word.isBlank() && translation.isBlank()) {
            showToast("Nothing to ask.")
            return
        }
        val text = if (word.isNotBlank()) word else translation
        val mode = if (word.isNotBlank()) EN_RU else RU_EN
        yandexRequest(text, mode)
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

    fun toNotificationSettings(@Suppress("UNUSED_PARAMETER") view: View) =
        startActivity(Intent(this, NotificationSchedulingActivity::class.java))

    fun learn(@Suppress("UNUSED_PARAMETER") view: View) {
        wilEdit.text?.clear()
        tilEdit.text?.clear()
        tilEdit.clearFocus()
        wilEdit.clearFocus()
        startActivity(Intent(this, GetLearningActivity::class.java))
    }

    fun pasteFromClipboardEn(@Suppress("UNUSED_PARAMETER") view: View) = pasteFromClipboard(wilEdit)
    fun pasteFromClipboardRu(@Suppress("UNUSED_PARAMETER") view: View) = pasteFromClipboard(tilEdit)

    private fun pasteFromClipboard(inputField: TextInputEditText) {
        val mode = when (inputField) {
            wilEdit -> EN_RU
            tilEdit -> RU_EN
            else -> {
                Log.e(MAIN_ACTIVITY, "You've passed invalid field in the paste function")
                showToast("WTF?! You've did smth, that breaks normal workflow of the app.")
                return
            }
        }
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
                    inputField.setText(text)
                    yandexRequest(text.toString(), mode)
                }
            } else showToast("Not a text in the clipboard!")
        } else showToast("The clipboard is empty!")
    }

    private fun yandexRequest(textOut: String, mode: String) {
        if (textOut.isBlank()) {
            Log.e(MAIN_ACTIVITY, "Passed text is blank. There will be no request to Yandex.")
            return
        }
        Log.i(MAIN_ACTIVITY, "Preparing request to Yandex...")
        GlobalScope.launch(Dispatchers.Main) {
            var data = ""
            HttpClient().use {
                data = it.get(String.format(YANDEX_REQUEST, url, key, textOut, mode))
            }
            Log.i(MAIN_ACTIVITY, "Request from yandex has been received: $data")
            if (data.isBlank()) return@launch
            val json = JSONObject(data)
            val code = json.get(CODE) as Int
            if (code == 200) {
                val lang = json.get(LANG)
                val resp = json.get(TEXT)
                if (resp is JSONArray && resp.length() > 0) {
                    when (lang) {
                        RU_EN -> wilEdit.setText(resp[0].toString())
                        EN_RU -> tilEdit.setText(resp[0].toString())
                        else -> {
                            Log.w(MAIN_ACTIVITY, "Unexpected response form Yandex: $lang")
                        }
                    }
                    switchYandexVisibility(true)
                } else {
                    Log.e(MAIN_ACTIVITY, "Yandex request failed: $data")
                }
            } else {
                Log.e(MAIN_ACTIVITY, "Yandex request failed: $data")
            }
        }
    }

    private fun switchYandexVisibility(show: Boolean) {
        yandexLicenseText.visibility = if (show) View.VISIBLE else View.GONE
        yandexLink.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showToast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).apply {
        setGravity(Gravity.TOP, 0, dumpDb.top - 170)
        show()
    }

    companion object {
        const val MAIN_ACTIVITY = "MAIN_ACTIVITY"
        const val RU_EN = "ru-en"
        const val EN_RU = "en-ru"
        const val YANDEX_REQUEST = "%s?key=%s&text=%s&lang=%s&format=plain"
        const val CODE = "code"
        const val LANG = "lang"
        const val TEXT = "text"
    }
}
