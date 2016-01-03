package havefun.chatter.ios.controller

import havefun.chatter.core.domain.Message
import havefun.chatter.core.presenter.ChatPresenter
import havefun.chatter.core.view.ChatView
import havefun.chatter.ios.adapter.MessageAdapter
import havefun.chatter.ios.injection.AppComponent
import havefun.chatter.ios.resource.Dimens.MessageList
import org.robovm.apple.coregraphics.CGAffineTransform
import org.robovm.apple.foundation.NSRange
import org.robovm.apple.uikit.*
import org.robovm.objc.annotation.CustomClass
import org.robovm.objc.annotation.IBOutlet

@CustomClass("ChatController")
class ChatController : BaseViewController(), ChatView {

    var messageListView: UITableView? = null @IBOutlet set
    var messageInput: UITextField? = null @IBOutlet set
    var sendButton: UIButton? = null @IBOutlet set

    lateinit var messageAdapter: MessageAdapter

    override val presenter: ChatPresenter by lazy { presenterFactory.presenter<ChatPresenter, ChatView>(::ChatPresenter, this) }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun viewDidLoad() {
        super.viewDidLoad()

        messageAdapter = MessageAdapter(messageListView!!)

        sendButton!!.isHidden = true
        messageListView!!.dataSource = messageAdapter
        messageInput!!.delegate = messageInputDelegate()
        sendButton!!.addOnTouchUpInsideListener(sendButtonOnTouchUpInsideListener())

        messageListView!!.estimatedRowHeight = MessageList.EST_ROW_HEIGHT
        messageListView!!.rowHeight = UITableView.getAutomaticDimension()
        messageListView!!.transform = CGAffineTransform.createScale(1.0, -1.0)
    }

    override fun clearMyMessageInput() {
        messageInput!!.text = ""
    }

    override fun hideSendButton() {
        sendButton!!.isHidden = true
    }

    override fun showSendButton() {
        sendButton!!.isHidden = false
    }

    override fun showMyMessage(message: Message) {
        messageAdapter.addMyMessage(message.text)
    }

    override fun showTheirMessage(message: Message) {
        messageAdapter.addTheirMessage(message.text)
    }

    private fun messageInputDelegate(): UITextFieldDelegateAdapter {
        return object : UITextFieldDelegateAdapter() {
            override fun shouldReturn(textField: UITextField?): Boolean {
                textField!!.resignFirstResponder()
                sendMyMessage()
                return true
            }

            override fun shouldChangeCharacters(textField: UITextField?, range: NSRange?, string: String?): Boolean {
                presenter.onMessageInputChanged(string!!)
                return true
            }
        }
    }

    private fun sendButtonOnTouchUpInsideListener(): UIControl.OnTouchUpInsideListener? {
        return object : UIControl.OnTouchUpInsideListener {
            override fun onTouchUpInside(control: UIControl?, event: UIEvent?) {
                sendMyMessage()
            }
        }
    }

    private fun sendMyMessage() {
        presenter.sendMessage(messageInput!!.text)
    }
}
