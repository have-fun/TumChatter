package havefun.chatter.core.presenter

import havefun.chatter.core.ChatClient
import havefun.chatter.core.domain.Message
import havefun.chatter.core.view.ChatView
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.mockito.Mockito.*
import rx.Observable
import testsupport.MockBaseView
import testsupport.TestHelper
import org.mockito.Mockito.`when` as onCalling

class ChatPresenterSpec : Spek() {
    init {
        val chatView = SimpleChatView()
        val mockChatClient = mock(ChatClient::class.java)

        chatView.myMessageInput = "Hi"
        chatView.isSendButtonVisible = true

        onCalling(mockChatClient.send(anyString())).then { Observable.just("Yo") }

        given("ChatPresenter") {
            val chatPresenter = chatView.presenter
            chatPresenter.chatClient = mockChatClient

            on("sending a message 'Hi'") {
                chatPresenter.sendMessage("Hi")

                it("should send 'Hi' to the chat client") {
                    verify(mockChatClient).send("Hi")
                }

                it("should display my message") {
                    assertThat(chatView.myLastMessageDisplayed).isEqualTo("Hi")
                }

                it("should clear my message input") {
                    assertThat(chatView.myMessageInput.isEmpty()).isTrue()
                }

                it("should hide the send button") {
                    assertThat(chatView.isSendButtonVisible).isFalse()
                }
            }
        }
    }

    class SimpleChatView : MockBaseView(), ChatView {
        var myMessageInput: String = ""
        var myLastMessageDisplayed: String = ""
        var isSendButtonVisible: Boolean = false

        override val presenter: ChatPresenter
            get() = TestHelper.coreComponent.presenterFactory.presenter<ChatPresenter, ChatView>(::ChatPresenter, this)

        override fun clearMyMessageInput() {
            myMessageInput = ""
        }

        override fun hideSendButton() {
            isSendButtonVisible = false
        }

        override fun showSendButton() {
            isSendButtonVisible = true
        }

        override fun showMyMessage(message: Message) {
            myLastMessageDisplayed = message.text
        }

        override fun showTheirMessage(message: Message) {
        }
    }
}