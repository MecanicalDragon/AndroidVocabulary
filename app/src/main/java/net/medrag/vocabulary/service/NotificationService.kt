package net.medrag.vocabulary.service

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
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

    val intentExtra = context.resources.getString(R.string.wordsFromNotificationIntent)

    init {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply { description = CHANNEL_DESC }
        context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
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

//    fun alarm() {
//        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager
//        val pendingIntent =
//            PendingIntent.getService(
//                context, 0, intent,
//                PendingIntent.FLAG_NO_CREATE
//            )
//        if (pendingIntent != null && alarmManager != null) {
//            alarmManager.cancel(pendingIntent)
//        }
//        alarmManager?.setInexactRepeating(
//            AlarmManager.ELAPSED_REALTIME_WAKEUP,
//            SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_HALF_HOUR,
//            AlarmManager.INTERVAL_HALF_HOUR,
//            alarmIntent
//        )
//    }

    private fun displayNotification(words: Int) {
        val pairs = database.getRandomSeveralPairs(words)
        val intent = Intent(context, NotificationIntentActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        intent.putExtra(intentExtra, pairs)

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, 0)
        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notification)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentTitle("Do you remember these words?")
            .setContentText(pairs.map { it.trans }.reduce { acc, string -> "$acc | $string" })
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(pairs.map { it.trans }.reduce { acc, string -> "$acc\n$string" }))
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
        NotificationManagerCompat.from(context)
            .notify((Math.random() * Integer.MAX_VALUE).toInt(), mBuilder.build())
    }

    companion object {
        private const val CHANNEL_ID = "vocab_translate_notifications"
        private const val CHANNEL_NAME = "MyVocabulary translation notifications"
        private const val CHANNEL_DESC = "Notifications to remind learning."
    }
}