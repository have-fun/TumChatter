package havefun.chatter.android

import android.app.Application
import havefun.chatter.android.injection.AppComponent
import havefun.chatter.android.injection.DaggerAppComponent
import havefun.chatter.android.resource.ENDPOINT
import havefun.chatter.android.resource.PORT
import havefun.chatter.core.injection.DaggerCoreComponent
import havefun.chatter.core.injection.PresenterModule
import havefun.chatter.core.injection.ServiceModule
import havefun.server.chatservice.ChatService

class ChatterApp : Application() {

    open val appComponent: AppComponent by lazy {
        val coreComponent = DaggerCoreComponent.builder()
                .presenterModule(PresenterModule())
                .serviceModule(ServiceModule(ENDPOINT))
                .build()

        DaggerAppComponent.builder().coreComponent(coreComponent).build()
    }

    override fun onCreate() {
        super.onCreate()
        ChatService(PORT).start()
    }
}