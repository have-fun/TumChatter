package havefun.chatter.ios.injection

import dagger.Component
import havefun.chatter.core.injection.ApplicationScope
import havefun.chatter.core.injection.CoreComponent
import havefun.chatter.ios.controller.ChatController

@ApplicationScope
@Component(modules = arrayOf(AppModule::class), dependencies = arrayOf(CoreComponent::class))
interface AppComponent {
    fun inject(controller: ChatController)
}