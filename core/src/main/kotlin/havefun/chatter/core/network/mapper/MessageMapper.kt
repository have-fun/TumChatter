package havefun.chatter.core.network.mapper

import havefun.chatter.core.domain.Message
import havefun.chatter.core.network.dto.MessageRequest
import havefun.chatter.core.network.dto.MessageResponse

class MessageMapper: Mapper<Message, MessageRequest, MessageResponse> {
    override fun toRequest(domain: Message): MessageRequest {
        return MessageRequest(domain.text)
    }

    override fun fromResponse(dto: MessageResponse): Message {
        return Message(dto.text)
    }
}