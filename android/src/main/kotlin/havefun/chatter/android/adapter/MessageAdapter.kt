package havefun.chatter.android.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import havefun.chatter.android.R

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.ViewHolder>() {

    val messageList: MutableList<MessageItem> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val resId = if (viewType == TYPE_MINE) R.layout.view_message_mine else R.layout.view_message_theirs
        val view = LayoutInflater.from(parent.context).inflate(resId, parent, false);

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = messageList[reverse(position)].text
    }

    override fun getItemCount() = messageList.size

    override fun getItemViewType(position: Int): Int {
        return if (messageList[reverse(position)].status == Status.RECEIVED) TYPE_THEIRS else TYPE_MINE
    }

    fun addMyMessage(text: String) {
        messageList.add(MessageItem(text, Status.SENDING))
        notifyDataSetChanged()
    }

    fun addTheirMessage(text: String) {
        messageList.add(MessageItem(text, Status.RECEIVED))
        notifyDataSetChanged()
    }

    private fun reverse(position: Int) = messageList.size - position - 1

    class ViewHolder(val itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView by bindView<TextView>(R.id.message)
    }

    // DELIVERED is not used at the moment
    enum class Status {
        RECEIVED, SENDING, DELIVERED
    }

    data class MessageItem(val text: String, val status: Status)

    companion object {
        private const val TYPE_MINE = 1
        private const val TYPE_THEIRS = 2
    }
}