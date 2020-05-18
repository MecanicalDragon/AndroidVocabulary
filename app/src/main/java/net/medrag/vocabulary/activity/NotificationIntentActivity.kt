package net.medrag.vocabulary.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notification_intent.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair

class NotificationIntentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_intent)
        val words: ArrayList<Pair>? = intent?.extras?.get("words") as? ArrayList<Pair>
        println("ANY:")
        wordPairs.text = words?.map { it.word + "\n----------\n" + it.trans }
            ?.reduce { acc, s -> "$acc\n==========\n$s" }
        println("DONE")
    }

}