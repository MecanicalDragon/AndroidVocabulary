package net.medrag.vocabulary.activity

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.SystemClock
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_notification_sheduling.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.service.NotificationBroadcaster

class NotificationSchedulingActivity : AppCompatActivity() {

    private var period: Long = 5_400_000L
    private var wordsCount: Int = 3
    private lateinit var sharedPref: SharedPreferences

    /**
     * On activity creation we fetch props from disk and assign it to the activity values
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = getSharedPreferences(
            getString(R.string.notificationSchedulingProps),
            Context.MODE_PRIVATE
        )
        wordsCount = sharedPref.getInt(
            getString(R.string.notificationSchedulingPropsWordsAmount),
            wordsCount
        )
        period = sharedPref.getLong(
            getString(R.string.notificationSchedulingPropsPeriod),
            period
        )
        setContentView(R.layout.activity_notification_sheduling)
        hoursInput.setText((period / MILLIS_IN_HOUR).toString())
        minutesInput.setText(((period % MILLIS_IN_HOUR) / MILLIS_IN_MINUTE).toString())
        pairsNumber.setText(wordsCount.toString())
    }

    /**
     * Save new scheduling properties, run or stop scheduled job, notify the user.
     */
    fun switchNotifications(@Suppress("UNUSED_PARAMETER") view: View) {
        wordsCount = readWordsCount()
        period = readPeriod()
        saveNewPreferences()
        val pendingIntent = createIntent()
        runScheduledJob(pendingIntent)
        showToast()
    }

    private fun saveNewPreferences() {
        with(sharedPref.edit()) {
            putLong(getString(R.string.notificationSchedulingPropsPeriod), period)
            putInt(getString(R.string.notificationSchedulingPropsWordsAmount), wordsCount)
            apply()
        }
    }

    private fun showToast() {
        val toastMessage = if (period == 0L || wordsCount == 0) "Notifications deactivated!"
        else "Notifications activated!"
        val makeText = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG)
        makeText.setGravity(Gravity.TOP, 0, updateNotifications.bottom + 400)
        makeText.show()
        finish()
    }

    /**
     * Assign scheduled job to the AlarmManager or stop it if all prop values are zeroes.
     */
    private fun runScheduledJob(pendingIntent: PendingIntent?) {

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as? AlarmManager

        if (pendingIntent != null && alarmManager != null)
            alarmManager.cancel(pendingIntent)

        if (period != 0L && wordsCount != 0) {
            alarmManager?.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + period, period,
                pendingIntent
            )
        }
    }

    /**
     * Create pending intent for notifications.
     */
    private fun createIntent(): PendingIntent? {

        val intent = Intent(this, NotificationBroadcaster::class.java).apply {
            //                flags = Intent.FLAG_ACTIVITY_NEW_TASK and Intent.FLAG_ACTIVITY_CLEAR_TASK
            flags = Intent.FLAG_INCLUDE_STOPPED_PACKAGES
            putExtra(resources.getString(R.string.wordsInNotificationAmount), wordsCount)
            action = resources.getString(R.string.wordsNotificationAction)
        }

        return PendingIntent.getBroadcast(
            this,
            resources.getInteger(R.integer.wordsNotificationPendingIntentRequestCode),
            intent,
            PendingIntent.FLAG_CANCEL_CURRENT
        )
    }

    /**
     * Read new period value from the input.
     */
    private fun readPeriod(): Long {
        val hoursText = hoursInput.text.toString()
        val hours = if (hoursText.isBlank()) 0L else hoursText.toLong() * MILLIS_IN_HOUR
        val minutesText = minutesInput.text.toString()
        val minutes = if (minutesText.isBlank()) 0L else minutesText.toLong() * MILLIS_IN_MINUTE
        return hours + minutes
    }

    /**
     * Read new wordsCount value from the input.
     */
    private fun readWordsCount(): Int {
        var wordsCount = when (val pairsNumber = pairsNumber.text.toString()) {
            "", "0" -> MIN_WORDS_COUNT
            else -> pairsNumber.toInt()
        }
        if (wordsCount > MAX_WORDS_COUNT) wordsCount = MAX_WORDS_COUNT
        else if (wordsCount < MIN_WORDS_COUNT) wordsCount = MIN_WORDS_COUNT
        return wordsCount
    }

    companion object {

        private const val MILLIS_IN_MINUTE = 60_000L
        private const val MILLIS_IN_HOUR = 3_600_000L

        private const val MIN_WORDS_COUNT = 0
        private const val MAX_WORDS_COUNT = 9
    }
}