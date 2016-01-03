package havefun.server.chatservice.injection

import dagger.Module
import dagger.Provides
import havefun.server.chatservice.helper.Brain
import havefun.server.chatservice.helper.SimpleBrain
import javax.inject.Singleton

@Module
class ChatServiceModule {
    @Provides
    @Singleton
    fun provideBrain(): Brain = SimpleBrain()
}