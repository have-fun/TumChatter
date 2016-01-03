package havefun.chatter.ios.adapter

import havefun.chatter.ios.view.BaseMessageViewCell
import org.robovm.apple.coregraphics.CGAffineTransform
import org.robovm.apple.foundation.NSIndexPath
import org.robovm.apple.uikit.UITableView
import org.robovm.apple.uikit.UITableViewCell
import org.robovm.apple.uikit.UITableViewDataSource
import org.robovm.apple.uikit.UITableViewDataSourceAdapter
import org.robovm.objc.annotation.CustomClass

@CustomClass("MessageAdapter")
class MessageAdapter(val messageListView: UITableView) : UITableViewDataSourceAdapter(), UITableViewDataSource {

    val messageList: MutableList<MessageItem> = arrayListOf()

    override fun getNumberOfSections(tableView: UITableView?) = 1L

    override fun getNumberOfRowsInSection(tableView: UITableView?, section: Long) = messageList.size.toLong()

    override fun getCellForRow(tableView: UITableView, indexPath: NSIndexPath?): UITableViewCell? {
        val message = messageList[reverse(indexPath!!.item)]
        val id = if (message.status == Status.RECEIVED) THEIR_MESSAGE_CELL_ID else MY_MESSAGE_CELL_ID

        val cell = tableView.dequeueReusableCell(id, indexPath) as BaseMessageViewCell
        cell.message = message.text
        cell.transform = CGAffineTransform.createScale(1.0, -1.0)

        return cell
    }

    fun addMyMessage(text: String) {
        messageList.add(MessageItem(text, Status.SENDING))
        messageListView.reloadData()
    }

    fun addTheirMessage(text: String) {
        messageList.add(MessageItem(text, Status.RECEIVED))
        messageListView.reloadData()
    }

    private fun reverse(position: Int) = messageList.size - position - 1

    enum class Status {
        RECEIVED, SENDING, DELIVERED
    }

    data class MessageItem(val text: String, val status: Status)

    companion object {
        private const val MY_MESSAGE_CELL_ID = "MyMessageViewCell"
        private const val THEIR_MESSAGE_CELL_ID = "TheirMessageViewCell"
    }
}