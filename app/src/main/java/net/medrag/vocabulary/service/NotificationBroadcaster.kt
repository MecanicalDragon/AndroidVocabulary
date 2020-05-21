package net.medrag.vocabulary.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import net.medrag.vocabulary.R
import net.medrag.vocabulary.activity.NotificationLandingActivity
import net.medrag.vocabulary.db.Pair
import net.medrag.vocabulary.db.Repository

class NotificationBroadcaster : BroadcastReceiver() {

    override fun onReceive(context: Context, incoming: Intent) {

        Log.i(NOTIFICATION, "It's notification time!")

        if (context.resources.getString(R.string.wordsNotificationAction) != incoming.action) {
            Log.e(NOTIFICATION, "Action type mismatch!")
            Log.e(
                NOTIFICATION,
                "Expected: ${context.resources.getString(R.string.wordsNotificationAction)}"
            )
            Log.e(NOTIFICATION, "Actual: ${incoming.action}")
            return
        }

        createChannel(context)
        val pairs = Repository(context).getRandomSeveralPairs(
            incoming.extras?.getInt(
                context.resources.getString(R.string.wordsInNotificationAmount)
            ) as Int
        )
        val intent = Intent(context, NotificationLandingActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            putParcelableArrayListExtra(
                context.resources.getString(R.string.notificationWordPairsSet),
                pairs
            )
        }

        val mBuilder = buildNotificationCompat(context, intent, pairs)
        NotificationManagerCompat.from(context)
            .notify((Math.random() * Integer.MAX_VALUE).toInt(), mBuilder.build())
    }

    private fun buildNotificationCompat(
        context: Context,
        intent: Intent,
        pairs: ArrayList<Pair>
    ): NotificationCompat.Builder {
        val pendingIntent = PendingIntent.getActivity(
            context,
            (Math.random() * Integer.MAX_VALUE).toInt(),
            intent,
            0
        )
        return NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_puzzle)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentTitle("Do you remember these words?")
            .setContentText(pairs.map { it.trans }.reduce { acc, string -> "$acc | $string" })
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(pairs.map { it.trans }.reduce { acc, string -> "$acc\n$string" })
            )
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    private fun createChannel(context: Context) {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        ).apply { description = CHANNEL_DESC }
        context.getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
    }

    companion object {
        private const val CHANNEL_ID = "vocab_translate_notifications"
        private const val CHANNEL_NAME = "MyVocabulary translation notifications"
        private const val CHANNEL_DESC = "Notifications to remind learning."
        private const val NOTIFICATION = "NOTIFICATION"
    }
}