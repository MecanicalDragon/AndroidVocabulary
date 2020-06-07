package net.medrag.vocabulary.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import net.medrag.vocabulary.R

class GetLearningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_get_learning)
    }

    fun startChallenge(view: View) {
        val amount = when ((view as Button).text) {
            resources.getString(R.string.Get10) -> 10
            resources.getString(R.string.Get25) -> 25
            resources.getString(R.string.Get50) -> 50
            resources.getString(R.string.Get100) -> 100
            else -> 10
        }
        startActivity(Intent(this, LearningActivity::class.java).apply {
            putExtra(resources.getString(R.string.pickAmount), amount)
        })
    }
}
