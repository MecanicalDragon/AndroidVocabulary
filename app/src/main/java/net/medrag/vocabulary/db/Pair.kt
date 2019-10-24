package net.medrag.vocabulary.db

data class Pair(
    var id: Int,
    var word: String,
    var trans: String,
    var streak: Int,
    var learned: Boolean
)