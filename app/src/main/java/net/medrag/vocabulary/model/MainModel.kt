package net.medrag.vocabulary.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class MainModel : ViewModel() {

    var word: MutableLiveData<String> = MutableLiveData<String>().apply { postValue("") }
    var translation: MutableLiveData<String> = MutableLiveData<String>().apply { postValue("") }
    var yandexVisibility: MutableLiveData<Boolean> =
        MutableLiveData<Boolean>().apply { postValue(false) }


    fun doYandexRequest(request: String) {
        viewModelScope.launch {
            var data = ""
            Log.i(MAIN_ACTIVITY, "Preparing request to Yandex...")
            HttpClient().use { data = it.get(request) }
            Log.i(MAIN_ACTIVITY, "Request from yandex has been received: $data")
            if (data.isBlank()) return@launch
            val json = JSONObject(data)
            val code = json.get(CODE) as Int
            if (code == 200) {
                val lang = json.get(LANG)
                val resp = json.get(TEXT)
                if (resp is JSONArray && resp.length() > 0) {
                    when (lang) {
                        RU_EN -> word.postValue(resp[0].toString())
                        EN_RU -> translation.postValue(resp[0].toString())
                        else -> {
                            Log.w(
                                MAIN_ACTIVITY,
                                "Unexpected response form Yandex: $lang"
                            )
                        }
                    }
                    yandexVisibility.postValue(true)
                } else {
                    Log.e(MAIN_ACTIVITY, "Yandex request failed: $data")
                }
            } else {
                Log.e(MAIN_ACTIVITY, "Yandex request failed: $data")
            }
        }
    }

    companion object {
        const val MAIN_ACTIVITY = "MAIN_ACTIVITY"
        const val RU_EN = "ru-en"
        const val EN_RU = "en-ru"
        const val CODE = "code"
        const val LANG = "lang"
        const val TEXT = "text"
    }
}