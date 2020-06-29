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
    }
}