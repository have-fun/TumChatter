package havefun.chatter.core.view

import havefun.chatter.core.domain.Message

interface ChatView : BaseView {
    fun clearMyMessageInput()

    fun hideSendButton()
    fun showSendButton()

    fun showMyMessage(message: Message)
    fun showTheirMessage(message: Message)
}
