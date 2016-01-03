package havefun.chatter.core.injection

import dagger.Component
import havefun.chatter.core.ChatClient
import havefun.chatter.core.presenter.ChatPresenter
import havefun.chatter.core.presenter.PresenterFactory
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(PresenterModule::class, ServiceModule::class))
interface CoreComponent {
    val component: CoreComponent
    val chatClient: ChatClient
    val presenterFactory: PresenterFactory

    fun inject(presenter: ChatPresenter)
}