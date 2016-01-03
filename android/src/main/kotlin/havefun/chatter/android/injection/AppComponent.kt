package havefun.chatter.android.injection

import dagger.Component
import havefun.chatter.android.activity.ChatActivity
import havefun.chatter.core.injection.ApplicationScope
import havefun.chatter.core.injection.CoreComponent

@ApplicationScope
@Component(modules = arrayOf(AppModule::class), dependencies = arrayOf(CoreComponent::class))
interface AppComponent {
    fun inject(view: ChatActivity)
}