package havefun.server.chatservice.injection

import dagger.Component
import havefun.server.chatservice.controller.MessageController
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ChatServiceModule::class))
interface ChatServiceComponent {
    fun inject(controller: MessageController)
}