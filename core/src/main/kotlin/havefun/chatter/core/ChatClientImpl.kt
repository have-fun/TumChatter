package havefun.chatter.core

import havefun.chatter.core.domain.Message
import havefun.chatter.core.network.ChatWebService
import havefun.chatter.core.network.mapper.Mappers
import rx.Observable

class ChatClientImpl(private val chatWebClient: ChatWebService, private val mappers: Mappers): ChatClient {

    override fun send(message: String): Observable<Message> {
        val request = mappers.messageMapper.toRequest(Message(message))
        return chatWebClient.send(request).map {
            mappers.messageMapper.fromResponse(it)
        }
    }
}