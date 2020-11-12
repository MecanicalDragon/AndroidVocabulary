package net.medrag.vocabulary.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import net.medrag.vocabulary.R
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class Repository(private val context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    private var database: SQLiteDatabase = this.writableDatabase

    init {
        Log.i(DATABASE, "Database path: ${database.path}")
        Log.i(DATABASE, "Database version: ${database.version}")
        if (database.version != DATABASE_VERSION) {
            Log.i(DATABASE, "Database upgrade incoming...")
            onUpgrade(database, database.version, DATABASE_VERSION)
            Log.i(
                DATABASE,
                "Database upgrade has been finished. Current version is $DATABASE_VERSION"
            )
        }
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
        try {
            db.beginTransaction()
            db.execSQL(CREATE)
            for (query in queries) {
                db.insert(VOCABULARY, null, query)
            }
            db.execSQL(UPDATE_1)
            update1(db)
            db.setTransactionSuccessful()
        } finally {
            if (db.inTransaction())
                db.endTransaction()
        }
        Log.i(DATABASE, "Database initialized.")
    }

    /**
     * Upgrade database
     */
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {
            db.beginTransaction()
            when (newVersion) {
                2 -> {
                    db.execSQL(UPDATE_1)
                    update1(db)
                }
            }
            db.setTransactionSuccessful()
        } finally {
            if (db.inTransaction())
                db.endTransaction()
        }
    }

    private fun update1(db: SQLiteDatabase) {
        val identifier =
            context.resources.getIdentifier("achievements", "raw", context.packageName)
        val input = context.resources.openRawResource(identifier)
        BufferedReader(InputStreamReader(input)).lines().map {
            ContentValues().apply {
                put(ACHIEVEMENT_NAME, it)
                put(ACHIEVEMENT_SCORE, 0)
            }
        }.forEachOrdered { db.insert(ACHIEVEMENT, null, it) }
    }

    fun getAchievements(): HashMap<String, Int> {
        (database.rawQuery(
            "select * from $ACHIEVEMENT union select 0, 'total', count() from $VOCABULARY",
            null
        )).use {
            val result = HashMap<String, Int>()
            val a = it.getColumnIndex(ACHIEVEMENT_NAME)
            val s = it.getColumnIndex(ACHIEVEMENT_SCORE)
            while (it.moveToNext()) {
                result[it.getString(a)] = it.getInt(s)
            }
            return result
        }
    }

    fun increaseAchievementScore(achievement: String) {
        database.execSQL(
            "update $ACHIEVEMENT set $ACHIEVEMENT_SCORE = $ACHIEVEMENT_SCORE + 1 where $ACHIEVEMENT_NAME = ?",
            arrayOf(achievement)
        )
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
        return database.insert(VOCABULARY, null, content)
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
            VOCABULARY,
            content,
            "$ID = ?",
            arrayOf(pair.id.toString())
        )
    }

    /**
     * Retrieves all pairs, matching specified pattern.
     */
    fun getAllLike(pattern: String): List<Pair> {
        if (pattern.length < 2) return emptyList()
        val like = "%$pattern%"
        (database.rawQuery(
            "select * from $VOCABULARY where $WORD like ? or $TRANSLATION like ?",
            arrayOf(like, like)
        )).use {
            return mapCursorToPairArray(it)
        }
    }

    /**
     * Retrieve worst learned words.
     */
    fun getWorstLearnedPairs(amount: Int): ArrayList<Pair> {
        (database.rawQuery(
            "select * from $VOCABULARY order by $STREAK ASC limit ?",
            arrayOf(amount.toString())
        )).use {
            return mapCursorToPairArray(it)
        }
    }

    /**
     * Retrieve random not learned yet words.
     */
    fun getRandomSeveralPairs(amount: Int): ArrayList<Pair> {
        val learnedMarker = context.resources.getInteger(R.integer.correctAnswersToLearnAmount)
        (database.rawQuery(
            "select * from $VOCABULARY where $STREAK < ? order by random() limit ?",
            arrayOf(learnedMarker.toString(), amount.toString())
        )).use {
            return mapCursorToPairArray(it)
        }
    }

    fun updateStreak(pairs: List<Pair>) {
        if (pairs.isEmpty()) return
        val inVar = pairs.map { it.id.toString() }.reduce { acc, s -> "$acc,$s" }
        val sql = "update $VOCABULARY set $STREAK = $STREAK + 1 where $ID in ($inVar)"
        Log.i(DATABASE, "Executing SQL: $sql")
        database.execSQL(sql)
    }

    /**
     * Get all saved words
     */
    fun extractAll(): List<Pair> = (database.rawQuery("select * from $VOCABULARY", null)).use {
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

    //
//    fun delete() {
//        database.delete(TABLE_NAME, "ID = ?", Array<String>(5) { "1" })
//    }

    companion object {

        const val DATABASE_VERSION = 2

        const val DATABASE = "DATABASE"
        const val DATABASE_NAME = "MyVocabulary.db"
        const val VOCABULARY = "VOCAB"
        const val ID = "ID"
        const val WORD = "WORD"
        const val TRANSLATION = "TRANSLATION"
        const val STREAK = "STREAK"
        const val LEARNED = "LEARNED"
        const val TAG = "TAG"

        private const val ACHIEVEMENT = "ACHIEVEMENT"
        private const val ACHIEVEMENT_NAME = "NAME"
        private const val ACHIEVEMENT_SCORE = "SCORE"

        const val CREATE =
            "CREATE TABLE IF NOT EXISTS $VOCABULARY ($ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$WORD TEXT, $TRANSLATION TEXT, $TAG TEXT, $STREAK INTEGER, $LEARNED NUMERIC);"

        private const val UPDATE_1 =
            "CREATE TABLE IF NOT EXISTS $ACHIEVEMENT ($ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$ACHIEVEMENT_NAME TEXT, $ACHIEVEMENT_SCORE INTEGER);"
    }
}