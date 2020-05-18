package net.medrag.vocabulary.activity

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notification.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.service.NotificationService

class NotificationActivity : AppCompatActivity() {
    private lateinit var notificationService: NotificationService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        notificationService = NotificationService(this)
        hoursInput.setText((notificationService.notifyPeriod / MILLIS_IN_HOUR).toString())
        minutesInput.setText(((notificationService.notifyPeriod % MILLIS_IN_HOUR) / MILLIS_IN_MINUTE).toString())
        pairsNumber.setText(notificationService.wordsInNotificationAmount.toString())
    }

    fun switchNotifications(@Suppress("UNUSED_PARAMETER") view: View) {
        var wordsCount = when (val pairsNumber = pairsNumber.text.toString()) {
            "", "0" -> MIN_WORDS_COUNT
            else -> pairsNumber.toInt()
        }
        if (wordsCount > MAX_WORDS_COUNT) wordsCount = MAX_WORDS_COUNT
        else if (wordsCount < MIN_WORDS_COUNT) wordsCount = MIN_WORDS_COUNT
        notificationService.wordsInNotificationAmount = wordsCount

        val hoursText = hoursInput.text.toString()
        val hours = if (hoursText.isBlank()) 0 else hoursText.toLong() * MILLIS_IN_HOUR
        val minutesText = minutesInput.text.toString()
        val minutes = if (minutesText.isBlank()) 0 else minutesText.toLong() * MILLIS_IN_MINUTE
        val total = hours + minutes
        notificationService.notifyPeriod = total
        notificationService.switchNotifications()

        val toastMessage =
            if (total == 0L || wordsCount == 0) "Notifications deactivated!" else "Notifications activated!"
        val makeText = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG)
        makeText.setGravity(Gravity.TOP, 0, updateNotifications.bottom + 200)
        makeText.show()
    }

    companion object {
        private const val MILLIS_IN_HOUR = 3600_000L
        private const val MILLIS_IN_MINUTE = 60_000L

        private const val MIN_WORDS_COUNT = 0
        private const val MAX_WORDS_COUNT = 9
    }
}