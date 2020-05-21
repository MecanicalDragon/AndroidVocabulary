package net.medrag.vocabulary.service

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.ProgressBar
import android.widget.TextView
import net.medrag.vocabulary.R
import net.medrag.vocabulary.db.Pair


class PairAdapter(context: Context, private val wordList: ArrayList<Pair>) : BaseAdapter() {

    private val lInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    private val answersToLearn = context.resources.getInteger(R.integer.correctAnswersToLearnAmount)

    override fun getCount() = wordList.size

    override fun getItem(position: Int) = wordList[position]

    override fun getItemId(position: Int) = wordList[position].id.toLong()

    /**
     * Return list item.
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {

        // here we try to use views, that have been created, but currently not used.
        var view: View? = convertView
        if (view == null) {
            view = lInflater.inflate(R.layout.pair_item, parent, false)
        }
        val pair = getItem(position)

        if (view != null) {
            (view.findViewById(R.id.wordInList) as TextView).text = ""
            (view.findViewById(R.id.transInList) as TextView).text = pair.trans
            val progress = defineProgressBar(view, pair)
            defineCheckbox(view, position, progress)
        }
        return view
    }

    private fun defineProgressBar(view: View, pair: Pair): ProgressBar {
        val progress = view.findViewById(R.id.learningProcess) as ProgressBar
        progress.max = answersToLearn
        progress.min = 0
        progress.progress = pair.streak
        return progress
    }

    private fun defineCheckbox(view: View, position: Int, progress: ProgressBar) {
        val wordTextView = view.findViewById(R.id.wordInList) as TextView
        val checkBox = view.findViewById(R.id.remembered) as CheckBox
        checkBox.tag = position
        checkBox.isChecked = false
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            val item = getItem(buttonView.tag as Int)
            if (isChecked) {
                wordTextView.text = item.word
                item.learned = true
                progress.progress = ++item.streak
            } else {
                wordTextView.text = ""
                item.learned = false
                progress.progress = --item.streak
            }
        }
    }
}