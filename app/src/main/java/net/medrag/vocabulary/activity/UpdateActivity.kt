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
        pair = intent.getParcelableExtra<Pair>("pair")
        wilEdit.setText(pair.word)
        tilEdit.setText(pair.trans)
        wilEdit.hint = pair.word
        tilEdit.hint = pair.trans
        database = Repository(this)
    }

    fun updatePair(@Suppress("UNUSED_PARAMETER") view: View) {
        pair.word = if (wilEdit.text.toString().isNotBlank()) wilEdit.text.toString() else pair.word
        pair.trans =
            if (tilEdit.text.toString().isNotBlank()) tilEdit.text.toString() else pair.trans
        database.update(pair)
        setResult(Activity.RESULT_OK, Intent())
        finish()
    }


}
