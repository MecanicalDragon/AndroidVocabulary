package net.medrag.vocabulary.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.medrag.vocabulary.R
import net.medrag.vocabulary.activity.NotificationIntentActivity
import net.medrag.vocabulary.db.Repository

class NotificationService(private val context: Context) {

    private var database: Repository = Repository(context)
    var notifyPeriod: Long = 5400_000
    var wordsInNotificationAmount = 3
    var lock = 0

    init {
        val channel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = CHANNEL_DESC
        val manager = context.getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    fun switchNotifications() {
        if (notifyPeriod == 0L || wordsInNotificationAmount == 0) {
            lock = 0
        } else {
            val key = (Math.random() * 100_000_000).toInt()
            lock = key
            //TODO: inline it!
            GlobalScope.launch {
                while (lock == key) {
                    delay(notifyPeriod)
                    val words = wordsInNotificationAmount
                    if (lock == key && words > 0) {
                        displayNotification(words)
                    }
                }
            }
        }
    }

    private fun displayNotification(words: Int) {
        val pairs = database.getRandomSeveralPairs(words)
        val intent = Intent(context, NotificationIntentActivity::class.java)
        intent.putExtra("words", pairs)

        val pendingIntent =
            PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setContentTitle("Do you remember these words?")
            .setContentText(pairs.map { it.trans }.reduce { acc, string -> acc + "\n" + string })
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
        val mNotificationMgr: NotificationManagerCompat = NotificationManagerCompat.from(context)
        mNotificationMgr.notify(1, mBuilder.build())
    }

    companion object {
        private const val CHANNEL_ID = "vocab_translate_notifications"
        private const val CHANNEL_NAME = "MyVocabulary translation notifications"
        private const val CHANNEL_DESC = "Notifications to remind learning."
    }
}