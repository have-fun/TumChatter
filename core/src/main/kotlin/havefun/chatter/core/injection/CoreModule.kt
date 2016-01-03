package havefun.chatter.core.injection

import dagger.Module

@Module(includes = arrayOf(PresenterModule::class, ServiceModule::class))
class CoreModule() {
}