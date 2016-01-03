package havefun.chatter.ios.view

import org.robovm.apple.uikit.UIImageView
import org.robovm.apple.uikit.UILabel
import org.robovm.apple.uikit.UITableViewCell

abstract class BaseMessageViewCell() : UITableViewCell() {

    abstract var messageLabel: UILabel?
    abstract var messageBubble: UIImageView?

    var message: String?
        get() {
            return messageLabel!!.text
        }
        set(value: String?) {
            updateLabel(messageLabel!!, messageBubble!!, value)
        }

    abstract protected fun updateLabel(messageLabel: UILabel, messageBubble: UIImageView, message: String?)
}