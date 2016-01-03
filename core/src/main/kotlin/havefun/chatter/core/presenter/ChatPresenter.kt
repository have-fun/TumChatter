package havefun.chatter.core.presenter

import havefun.chatter.core.ChatClient
import havefun.chatter.core.domain.Message
import havefun.chatter.core.injection.CoreComponent
import havefun.chatter.core.view.ChatView
import javax.inject.Inject

class ChatPresenter(component: CoreComponent, view: ChatView) : BasePresenter<ChatView>(component, view) {

    @Inject lateinit var chatClient: ChatClient

    override fun inject(component: CoreComponent) {
        component.inject(this)
    }

    fun onMessageInputChanged(input: String) {
        if (input.isEmpty()) {
            view!!.hideSendButton()
        } else {
            view!!.showSendButton()
        }
    }

    fun sendMessage(text: String) {
        if (!text.isEmpty()) {
            view!!.showMyMessage(Message(text))
            view!!.clearMyMessageInput()
            view!!.hideSendButton()

            chatClient.send(text).subscribe(object : BaseSubscriber<Message>(view!!) {
                override fun onNext(reply: Message) {
                    view?.runOnUiThread { view?.showTheirMessage(reply) }
                }
            })
        }
    }
}
