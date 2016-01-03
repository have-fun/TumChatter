package havefun.server.chatservice

import havefun.server.chatservice.controller.MessageController
import havefun.server.chatservice.injection.ChatServiceModule
import havefun.server.chatservice.injection.DaggerChatServiceComponent
import havefun.tumweb.Host.Method.POST
import havefun.tumweb.Service

class ChatService(port: Int) : Service(port) {
    override fun initRouter() {
        route(POST, "/send") { MessageController().send(it) }
    }

    companion object {
        var component = DaggerChatServiceComponent.builder().chatServiceModule(ChatServiceModule()).build()
    }
}
