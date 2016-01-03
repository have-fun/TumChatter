package havefun.chatter.core

import havefun.chatter.core.domain.Message
import retrofit.http.GET
import retrofit.http.POST
import rx.Observable

interface ChatClient {
    fun send(message: String): Observable<Message>
}
