package havefun.chatter.core.network.mapper

import havefun.chatter.core.domain.Message
import org.assertj.core.api.Assertions
import org.jetbrains.spek.api.Spek

class MappersSpec: Spek() { init {
    given("MessageMapper") {
        val mapper = Mappers().messageMapper

        on("creating a MessageRequest") {
            val message = Message("Hello")
            val messageRequest = mapper.toRequest(message)

            it("should return a MessageRequest with the same text") {
                Assertions.assertThat("").isEqualTo("")
                Assertions.assertThat(messageRequest.text).isEqualTo("Hello")
            }
        }
    }
}}