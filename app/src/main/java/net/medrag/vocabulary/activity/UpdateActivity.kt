package net.medrag.vocabulary.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_update.*
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair
import net.medrag.vocabulary.db.Repository


class UpdateActivity : AppCompatActivity() {

    private lateinit var database: Repository
    private lateinit var pair: Pair

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        pair = intent.getParcelableExtra(resources.getString(R.string.pairExtra))
        wordInput.setText(pair.word)
        translationInput.setText(pair.trans)
        wordInput.hint = pair.word
        translationInput.hint = pair.trans
        database = Repository(this)
    }

    fun updatePair(@Suppress("UNUSED_PARAMETER") view: View) {
        pair.word = if (wordInput.text.toString().isNotBlank()) wordInput.text.toString() else pair.word
        pair.trans =
            if (translationInput.text.toString().isNotBlank()) translationInput.text.toString() else pair.trans
        pair.streak = 0
        database.update(pair)
        setResult(Activity.RESULT_OK, Intent())
        finish()
    }
}
