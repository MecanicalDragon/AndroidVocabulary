package net.medrag.vocabulary.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import net.medrag.vocabulary.R
import java.lang.StringBuilder
import java.util.*
import kotlin.collections.ArrayList

class Repository(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

    private var database: SQLiteDatabase = this.writableDatabase

    init {
        Log.i(DATABASE, "Database path: ${database.path}")
    }

    /**
     * Creating database
     */
    override fun onCreate(db: SQLiteDatabase) {
        Log.i(DATABASE, "Database initializing...")

        /**
         * prepare multiple insert queries
         */
        val identifier =
            context.resources.getIdentifier("vocabulary", "raw", context.packageName)
        val input = context.resources.openRawResource(identifier)
        val scanner = Scanner(input)
        val queries = ArrayList<ContentValues>()
        while (scanner.hasNext()) {
            val nextLine = scanner.nextLine()
            val split = nextLine.split("||")
            val content = ContentValues()
            content.put(WORD, split[0])
            content.put(TRANSLATION, split[1])
            content.put(TAG, "common")
            content.put(STREAK, 0)
            content.put(LEARNED, false)
            queries.add(content)
        }

        /**
         * do a transaction
         */
        db.beginTransaction()
        try {
            db.execSQL(CREATE)
            for (query in queries) {
                db.insert(TABLE_NAME, null, query)
            }
            db.setTransactionSuccessful()
        } finally {
            db.endTransaction()
        }
        Log.i(DATABASE, "Database initialized.")
    }

    /**
     * Upgrade database
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("")
    }

    /**
     * Add new word pair
     */
    fun addWord(word: String, trans: String): Long {
        val content = ContentValues()
        content.put(WORD, word)
        content.put(TRANSLATION, trans)
        content.put(STREAK, 0)
        content.put(LEARNED, false)
        return database.insert(TABLE_NAME, null, content)
    }

    /**
     * Retrieve random not learned yet words.
     */
    fun getRandomSeveralPairs(amount: Int): ArrayList<Pair> {
        var total = 1
        val learnedMarker = context.resources.getInteger(R.integer.correctAnswersToLearnAmount)
        (database.rawQuery(
            "select count($ID) as count from $TABLE_NAME where $STREAK < ?",
            arrayOf(learnedMarker.toString())
        )).use {
            it.moveToFirst()
            total = it.getInt(0)
        }
        val sb = StringBuilder()
        for (i in 1..amount) sb.append((Math.random() * total + 1).toInt()).append(",")
        val array = sb.deleteCharAt(sb.lastIndex).toString()
        (database.rawQuery(
            "select * from $TABLE_NAME where $STREAK < ? and ROWID in($array)",
            arrayOf(learnedMarker.toString())
        )).use {
            return mapCursorToPairArray(it)
        }
    }

    fun updateStreak(pairs: List<Pair>) {
        val inVar = pairs.map { it.id.toString() }.reduce { acc, s -> "$acc,$s" }
        val sql = "update $TABLE_NAME set $STREAK = $STREAK + 1 where $ID in ($inVar)"
        Log.i(DATABASE, "Executing SQL: $sql")
        database.execSQL(sql)
    }

    /**
     * Get all saved words
     */
    fun extractAll(): List<Pair> = (database.rawQuery("select * from $TABLE_NAME", null)).use {
        return mapCursorToPairArray(it)
    }

    private fun mapCursorToPairArray(cursor: Cursor): ArrayList<Pair> {
        val result = ArrayList<Pair>()
        val id = cursor.getColumnIndex(ID)
        val word = cursor.getColumnIndex(WORD)
        val streak = cursor.getColumnIndex(STREAK)
        val learned = cursor.getColumnIndex(LEARNED)
        val trans = cursor.getColumnIndex(TRANSLATION)
        while (cursor.moveToNext()) {
            result.add(
                Pair(
                    cursor.getInt(id),
                    cursor.getString(word),
                    cursor.getString(trans),
                    cursor.getInt(streak),
                    cursor.getInt(learned) > 0
                )
            )
        }
        return result
    }

    /**
     * Update existing pair
     */
    fun update(pair: Pair) {
        val content = ContentValues()
        content.put(WORD, pair.word)
        content.put(TRANSLATION, pair.trans)
        content.put(STREAK, pair.streak)
        content.put(LEARNED, pair.learned)
        database.update(
            TABLE_NAME,
            content,
            "$ID = ?",
            Array(1) { pair.id.toString() })
    }

    //
//    fun delete() {
//        database.delete(TABLE_NAME, "ID = ?", Array<String>(5) { "1" })
//    }

    companion object {
        const val DATABASE = "DATABASE"
        const val DATABASE_NAME = "MyVocabulary.db"
        const val TABLE_NAME = "VOCAB"
        const val ID = "ID"
        const val WORD = "WORD"
        const val TRANSLATION = "TRANSLATION"
        const val STREAK = "STREAK"
        const val LEARNED = "LEARNED"
        const val TAG = "TAG"

        const val CREATE =
            "CREATE TABLE IF NOT EXISTS $TABLE_NAME ($ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$WORD TEXT, $TRANSLATION TEXT, $TAG TEXT, $STREAK INTEGER, $LEARNED NUMERIC);"
    }
}