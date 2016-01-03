package havefun.server.chatservice.controller

import havefun.server.chatservice.ChatService
import havefun.server.chatservice.helper.Brain
import havefun.server.chatservice.network.dto.MessageRequest
import havefun.server.chatservice.network.dto.MessageResponse
import havefun.tumweb.Request
import havefun.tumweb.Response
import havefun.tumweb.View
import javax.inject.Inject

class MessageController {
    @Inject lateinit var brain: Brain

    init {
        ChatService.component.inject(this)
    }

    fun send(request: Request): Response {
        val messageRequest = request.model(MessageRequest::class.java)

        val responseText = brain.respond(messageRequest.text)

        return View(MessageResponse(responseText)).json
    }
}