package me.tylerbwong.stack.ui.drafts

import android.view.ViewGroup
import me.tylerbwong.stack.R
import me.tylerbwong.stack.databinding.AnswerDraftHolderBinding
import me.tylerbwong.stack.ui.adapter.ViewBindingViewHolder
import me.tylerbwong.stack.ui.home.AnswerDraftItem
import me.tylerbwong.stack.ui.questions.detail.QuestionDetailActivity
import me.tylerbwong.stack.ui.utils.markdown.setMarkdown
import me.tylerbwong.stack.ui.utils.setThrottledOnClickListener
import me.tylerbwong.stack.ui.utils.toHtml

class AnswerDraftHolder(
    container: ViewGroup
) : ViewBindingViewHolder<AnswerDraftItem, AnswerDraftHolderBinding>(
    container,
    AnswerDraftHolderBinding::inflate
) {
    override fun AnswerDraftHolderBinding.bind(item: AnswerDraftItem) {
        val draft = item.draft
        questionTitle.text = draft.questionTitle.toHtml()
        timestamp.text = itemView.context.getString(R.string.last_updated, draft.formattedTimestamp)
        draftPreview.setMarkdown(draft.bodyMarkdown)

        itemView.setThrottledOnClickListener {
            val intent = QuestionDetailActivity.makeIntent(
                context = it.context,
                id = draft.questionId,
                isInAnswerMode = true
            )
            it.context.startActivity(intent)
        }
    }
}
