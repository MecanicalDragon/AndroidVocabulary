package net.medrag.vocabulary.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_achievements.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Repository

class AchievementsActivity : AppCompatActivity() {

    private lateinit var database: Repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_achievements)
        database = Repository(this)
    }

    override fun onResume() {
        super.onResume()
        val achievements = database.getAchievements()
        get10GoldenCupScore.text = (achievements["get10GoldenCup"] ?: 0).toString()
        get25GoldenCupScore.text = (achievements["get25GoldenCup"] ?: 0).toString()
        get50GoldenCupScore.text = (achievements["get50GoldenCup"] ?: 0).toString()
        get100GoldenCupScore.text = (achievements["get100GoldenCup"] ?: 0).toString()

        get10SilverCupScore.text = (achievements["get10SilverCup"] ?: 0).toString()
        get25SilverCupScore.text = (achievements["get25SilverCup"] ?: 0).toString()
        get50SilverCupScore.text = (achievements["get50SilverCup"] ?: 0).toString()
        get100SilverCupScore.text = (achievements["get100SilverCup"] ?: 0).toString()

        get10BronzeCupScore.text = (achievements["get10BronzeCup"] ?: 0).toString()
        get25BronzeCupScore.text = (achievements["get25BronzeCup"] ?: 0).toString()
        get50BronzeCupScore.text = (achievements["get50BronzeCup"] ?: 0).toString()
        get100BronzeCupScore.text = (achievements["get100BronzeCup"] ?: 0).toString()

        val totalWords = achievements["total"] ?: 0
        totalAddedNumber.text = (totalWords).toString()
        totalAddedImage.setImageDrawable(
            resources.getDrawable(
                when (totalWords) {
                    in 0..50 -> R.drawable.book1
                    in 51..100 -> R.drawable.book2
                    in 101..250 -> R.drawable.book3
                    in 251..500 -> R.drawable.book4
                    in 501..1000 -> R.drawable.book5
                    in 1001..2500 -> R.drawable.book6
                    else -> R.drawable.book7
                }
            )
        )

        val allCorrect = achievements["allCorrect10"] ?: 0
        notificationStreakNumber.text = (allCorrect).toString()
        notificationStreakImage.setImageDrawable(
            resources.getDrawable(
                when (allCorrect) {
                    in 0..10 -> R.drawable.b1
                    in 11..50 -> R.drawable.b2
                    in 51..100 -> R.drawable.b3
                    in 101..250 -> R.drawable.b4
                    in 251..500 -> R.drawable.b5
                    in 501..1000 -> R.drawable.b6
                    in 1001..2500 -> R.drawable.b7
                    else -> R.drawable.b8
                }
            )
        )
    }
}