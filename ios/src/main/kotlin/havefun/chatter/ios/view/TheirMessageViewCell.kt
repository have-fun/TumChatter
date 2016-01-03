package havefun.chatter.ios.view

import havefun.chatter.ios.resource.Dimens.TheirMessageBubble.RESIZABLE_BOTTOM
import havefun.chatter.ios.resource.Dimens.TheirMessageBubble.RESIZABLE_LEFT
import havefun.chatter.ios.resource.Dimens.TheirMessageBubble.RESIZABLE_RIGHT
import havefun.chatter.ios.resource.Dimens.TheirMessageBubble.RESIZABLE_TOP
import havefun.chatter.ios.resource.IdRes.Companion.THEIR_IMAGE_BUBBLE
import org.robovm.apple.uikit.UIEdgeInsets
import org.robovm.apple.uikit.UIImage
import org.robovm.apple.uikit.UIImageView
import org.robovm.apple.uikit.UILabel
import org.robovm.objc.annotation.CustomClass
import org.robovm.objc.annotation.IBOutlet

@CustomClass("TheirMessageViewCell")
class TheirMessageViewCell : BaseMessageViewCell() {

    // @IBOutlet needs to be defined in subclasses on set
    override var messageLabel: UILabel? = null @IBOutlet set
    override var messageBubble: UIImageView? = null @IBOutlet set

    override fun updateLabel(messageView: UILabel, messageBubble: UIImageView, message: String?) {
        messageView.text = message

        val insets = UIEdgeInsets(RESIZABLE_TOP, RESIZABLE_LEFT, RESIZABLE_BOTTOM, RESIZABLE_RIGHT)
        val bubble = UIImage.getImage(THEIR_IMAGE_BUBBLE).newResizableImage(insets)
        messageBubble?.image = bubble
    }
}