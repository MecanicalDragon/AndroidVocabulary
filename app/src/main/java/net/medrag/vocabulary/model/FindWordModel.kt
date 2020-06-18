package net.medrag.vocabulary.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.medrag.vocabulary.db.Pair

class FindWordModel : ViewModel() {
    val pattern: MutableLiveData<String> = MutableLiveData("")
    val words: MutableLiveData<List<Pair>> = MutableLiveData(ArrayList())
    val iter: MutableLiveData<Int> = MutableLiveData(0)
    val pair: MutableLiveData<Pair> = MutableLiveData(Pair.EMPTY_PAIR)
    val wordsTotal: MutableLiveData<String> = MutableLiveData("")

    fun switchToNextPair(){
        pair.value = words.value?.get(iter.value ?: 0)
        iter.value = iter.value?.inc()
        wordsTotal.value = "${iter.value}/${words.value?.size}"
    }

    fun setNewWordList(words: List<Pair>, pattern: String){
        iter.value = 0
        this.words.value = words
        this.pattern.value = pattern
    }
}