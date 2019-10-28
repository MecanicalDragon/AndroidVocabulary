package net.medrag.vocabulary.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Repository(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {

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
        val queryArray = FILL.split("\n")
        val queries = ArrayList<ContentValues>()
        for (query in queryArray) {
            val content = ContentValues()
            content.put(WORD, query.split("'")[1])
            content.put(TRANSLATION, query.split("'")[3])
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
     * Get all saved words
     */
    fun extractAll(): List<Pair> {

        val cursor = database.rawQuery("select * from $TABLE_NAME", null)
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
        cursor.close()
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

}