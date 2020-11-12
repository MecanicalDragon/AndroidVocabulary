package net.medrag.vocabulary.activity

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notification_landing.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair
import net.medrag.vocabulary.db.Repository
import net.medrag.vocabulary.service.PairAdapter

class NotificationLandingActivity : AppCompatActivity() {

    lateinit var words: ArrayList<Pair>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_landing)
        words =
            intent?.extras?.getParcelableArrayList(resources.getString(R.string.notificationWordPairsSet))
                ?: ArrayList()
        pairList.adapter = PairAdapter(this, words)
    }

    fun saveProgress(@Suppress("UNUSED_PARAMETER") view: View) {
        Repository(this).updateStreak(words.filter { it.learned }.toList())
        val makeText =
            Toast.makeText(this, resources.getString(R.string.progressSaved), Toast.LENGTH_SHORT)
        makeText.setGravity(Gravity.TOP, 0, saveProgress.top - 300)
        makeText.show()
        startActivity(Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NO_HISTORY
            putExtra(resources.getString(R.string.closeImmediately), true)
        })
        finish()
    }
}