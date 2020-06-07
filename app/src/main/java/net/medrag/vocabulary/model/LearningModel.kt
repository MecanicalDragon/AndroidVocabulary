package net.medrag.vocabulary.model

import androidx.lifecycle.ViewModel
import net.medrag.vocabulary.db.Pair

class LearningModel : ViewModel() {

    var voc: List<Pair> = ArrayList()
    var iterator = 0
    var mistakesCounter = 0

    fun getTranslation() = voc[iterator].trans
    fun getWordsAmount() = "$iterator/${voc.size}"
}