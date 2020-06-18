package net.medrag.vocabulary.db

data class ReducedPair(val wordRu: String, val wordEn: String, val streak: Int) {
    override fun toString() = "$wordEn||$wordRu||$streak"
}