package havefun.chatter.android.activity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.KeyEvent
import android.view.View
import butterknife.OnClick
import butterknife.OnEditorAction
import butterknife.OnTextChanged
import havefun.chatter.android.R
import havefun.chatter.android.adapter.MessageAdapter
import havefun.chatter.android.injection.AppComponent
import havefun.chatter.core.domain.Message
import havefun.chatter.core.presenter.ChatPresenter
import havefun.chatter.core.view.ChatView
import kotlinx.android.synthetic.main.activity_chat.*

class ChatActivity : BaseActivity(), ChatView {

    val messageAdapter = MessageAdapter()

    override val presenter: ChatPresenter by lazy { presenterFactory.presenter<ChatPresenter, ChatView>(::ChatPresenter, this) }

    override fun inject(component: AppComponent) {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_chat)
        initMessageList()
    }

    override fun clearMyMessageInput() {
        messageInput.setText("")
    }

    override fun hideSendButton() {
        sendButton.visibility = View.INVISIBLE
    }

    override fun showSendButton() {
        sendButton.visibility = View.VISIBLE
    }

    override fun showMyMessage(message: Message) {
        messageAdapter.addMyMessage(message.text)
    }

    override fun showTheirMessage(message: Message) {
        messageAdapter.addTheirMessage(message.text)
    }

    @OnTextChanged(R.id.messageInput)
    fun onMessageInputTextChanged() {
        presenter.onMessageInputChanged(messageInput.text.toString())
    }

    @OnEditorAction(R.id.messageInput)
    fun onMessageInputEditorAction(event: KeyEvent, keyCode: Int): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN && (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_DPAD_CENTER)) {
            sendMyMessage()
            return true
        }

        return false
    }

    @OnClick(R.id.sendButton)
    fun onSendButtonClicked() {
        sendMyMessage()
    }

    private fun initMessageList() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        layoutManager.reverseLayout = true

        messageListView.layoutManager = layoutManager
        messageListView.adapter = messageAdapter
    }

    private fun sendMyMessage() {
        presenter.sendMessage(messageInput.text.toString())
    }
}

