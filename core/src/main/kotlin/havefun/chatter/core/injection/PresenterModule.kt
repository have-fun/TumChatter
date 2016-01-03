package havefun.chatter.core.injection

import dagger.Module
import dagger.Provides
import havefun.chatter.core.presenter.PresenterFactory
import javax.inject.Singleton

@Module
open class PresenterModule {

    @Provides
    @Singleton
    open fun providePresenterFactory(component: CoreComponent): PresenterFactory = PresenterFactory(component)
}