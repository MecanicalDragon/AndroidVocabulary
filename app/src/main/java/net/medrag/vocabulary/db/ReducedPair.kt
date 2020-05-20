package net.medrag.vocabulary.db

data class ReducedPair(val wordRu: String, val wordEn: String) {
    override fun toString() = "$wordEn||$wordRu"
}