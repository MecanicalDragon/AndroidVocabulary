package net.medrag.vocabulary.activity

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notification_landing.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair

class NotificationLandingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_landing)
        val words: ArrayList<Pair> =
            intent?.extras?.getParcelableArrayList(resources.getString(R.string.notificationWordPairsSet))
                ?: ArrayList()
        val array = words.map { it.word + "\n" + it.trans }.toTypedArray()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, array)
        pairList.adapter = adapter
    }
}