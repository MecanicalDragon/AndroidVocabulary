package net.medrag.vocabulary.model

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.medrag.vocabulary.R
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

class MainModel : ViewModel() {

    var word: MutableLiveData<String> = MutableLiveData<String>().apply { postValue("") }
    var translation: MutableLiveData<String> = MutableLiveData<String>().apply { postValue("") }
    var yandexVisibility: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { postValue(false) }

    fun doYandexRequest(q: String, mode: Translation, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i(MAIN_ACTIVITY, "Preparing request to Yandex...")
            try {
                val req = Request.Builder()
                    .url(context.resources.getString(R.string.yandexApiRequestUri))
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("x-rapidapi-key", context.resources.getString(R.string.yandexApiKey))
                    .addHeader("accept-encoding", "application/gzip")
                    .addHeader("x-rapidapi-host", "google-translate1.p.rapidapi.com")
                    .post("q=$q&source=${mode.source}&target=${mode.target}".toRequestBody())
                    .build()
                val resp = OkHttpClient().newCall(req).execute()
                if (resp.code == 200) {
                    val data = resp.body?.string() ?: ""
                    Log.i(MAIN_ACTIVITY, "Request from google has been received: $data")
                    if (data.isBlank()) return@launch
                    val translate = JSONObject(data).getJSONObject("data")
                        .getJSONArray("translations").getJSONObject(0).getString("translatedText")
                    if (translate.isNullOrBlank().not()) {
                        when (mode) {
                            Translation.RU -> word.postValue(translate.toString())
                            Translation.EN -> translation.postValue(translate.toString())
                        }
                        yandexVisibility.postValue(true)
                    } else {
                        Log.e(MAIN_ACTIVITY, "Google request failed: $data")
                    }
                } else showToast(context, resp.message)
            } catch (e: Exception) {
                Log.e(MAIN_ACTIVITY, "Exception during Google request!")
                Log.e(MAIN_ACTIVITY, e.toString())
                showToast(context, e.message ?: "Something went wrong...")
            }
        }
    }

    private fun showToast(context: Context, message: String) {
        Toast.makeText(
            context,
            message,
            Toast.LENGTH_LONG
        ).apply {
            setGravity(Gravity.TOP, 0, 100)
            show()
        }
    }

    companion object {
        const val MAIN_ACTIVITY = "MAIN_ACTIVITY"

        enum class Translation(val source: String, val target: String) {
            EN("en", "ru"),
            RU("ru", "en");
        }
    }
}