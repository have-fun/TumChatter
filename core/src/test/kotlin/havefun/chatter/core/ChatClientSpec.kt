package havefun.chatter.core

import havefun.server.chatservice.ChatService
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import testsupport.PORT
import testsupport.TestHelper
import java.util.concurrent.CountDownLatch

class ChatClientSpek : Spek() {
    init {
        given("ChatClient") {
            ChatService(PORT).start()

            val component = TestHelper.coreComponent
            val chatClient = component.chatClient;

            on("sending 'Hello'") {
                it("should get a 'Hi' response") {
                    val latch = CountDownLatch(1);
                    var reply = ""
                    chatClient.send("Hello").subscribe({ result ->
                        reply = result.text
                        latch.countDown()
                    }, { error ->
                        reply = if (error.message == null) "" else error.message.toString()
                        latch.countDown()
                    })

                    latch.await()

                    assertThat(reply).isEqualTo("Hi")
                }
            }
        }
    }
}