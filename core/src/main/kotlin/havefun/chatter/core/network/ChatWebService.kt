package havefun.chatter.core.network

import havefun.chatter.core.network.dto.MessageRequest
import havefun.chatter.core.network.dto.MessageResponse
import retrofit.http.Body
import retrofit.http.POST
import rx.Observable

interface ChatWebService {
    @POST("/send")
    fun send(@Body message: MessageRequest): Observable<MessageResponse>
}