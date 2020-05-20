package net.medrag.vocabulary.service

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_notification_landing)
//        val words: ArrayList<Pair> =
//            intent?.extras?.getParcelableArrayList(resources.getString(R.string.notificationWordPairsSet))
//                ?: ArrayList()
//        val adapter = ArrayAdapter(
//            this,
//            android.R.layout.simple_list_item_1,
//            words.map { it.word + "\n" + it.trans }.toList()
//        )
//        println(words)
//        pairList.adapter = adapter
//    }

//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.app.PendingIntent
//import android.app.Service
//import android.content.Intent
//import android.os.IBinder
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import net.medrag.vocabulary.R
//import net.medrag.vocabulary.activity.NotificationLandingActivity
//import net.medrag.vocabulary.db.Repository
//
//class NotificationService : Service() {
//
//    private lateinit var database: Repository
//
//    var notifyPeriod: Long = 5_000
//    var wordsInNotificationAmount = 5
//    var lock = 0
//
//    val intentExtra = "notification_words_intent"
//
//    override fun onCreate() {
//        super.onCreate()
//        println("CREATE")
//        database = Repository(this)
//        val channel = NotificationChannel(
//            CHANNEL_ID,
//            CHANNEL_NAME,
//            NotificationManager.IMPORTANCE_HIGH
//        ).apply { description = CHANNEL_DESC }
//        getSystemService(NotificationManager::class.java).createNotificationChannel(channel)
//    }
//
//    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        println("STARTED-COMMAND")
//        displayNotification(4)
//        stopSelf()
//        return super.onStartCommand(intent, flags, startId)
//    }
//
//    private fun displayNotification(words: Int) {
//        val pairs = database.getRandomSeveralPairs(words)
//        val intent = Intent(this, NotificationLandingActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//        intent.putExtra(intentExtra, pairs)
//
//        val pendingIntent =
//            PendingIntent.getActivity(this, 0, intent, 0)
//        val mBuilder: NotificationCompat.Builder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_notification)
//            .setPriority(NotificationCompat.PRIORITY_MAX)
//            .setContentTitle("Do you remember these words?")
//            .setContentText(pairs.map { it.trans }.reduce { acc, string -> "$acc | $string" })
//            .setStyle(
//                NotificationCompat.BigTextStyle()
//                    .bigText(pairs.map { it.trans }.reduce { acc, string -> "$acc\n$string" })
//            )
//            .setContentIntent(pendingIntent)
//            .setAutoCancel(true)
//        NotificationManagerCompat.from(this)
//            .notify((Math.random() * Integer.MAX_VALUE).toInt(), mBuilder.build())
//    }
//
//    companion object {
//        private const val CHANNEL_ID = "vocab_translate_notifications"
//        private const val CHANNEL_NAME = "MyVocabulary translation notifications"
//        private const val CHANNEL_DESC = "Notifications to remind learning."
//        private const val ALARM_INTENT = 128
//    }
//
//    override fun onBind(p0: Intent?): IBinder? {
//        return null
//    }
//}