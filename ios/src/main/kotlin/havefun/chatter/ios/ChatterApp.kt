package havefun.chatter.ios

import havefun.chatter.core.injection.DaggerCoreComponent
import havefun.chatter.core.injection.PresenterModule
import havefun.chatter.core.injection.ServiceModule
import havefun.chatter.ios.injection.AppComponent
import havefun.chatter.ios.injection.DaggerAppComponent
import havefun.chatter.ios.resource.ENDPOINT
import havefun.chatter.ios.resource.PORT
import havefun.server.chatservice.ChatService
import org.robovm.apple.foundation.NSAutoreleasePool
import org.robovm.apple.uikit.UIApplication
import org.robovm.apple.uikit.UIApplicationDelegateAdapter
import org.robovm.apple.uikit.UIApplicationLaunchOptions

class ChatterApp : UIApplicationDelegateAdapter() {

    open val appComponent: AppComponent by lazy {
        val coreComponent = DaggerCoreComponent.builder()
                .presenterModule(PresenterModule())
                .serviceModule(ServiceModule(ENDPOINT))
                .build()

        DaggerAppComponent.builder().coreComponent(coreComponent).build()
    }

    override fun didFinishLaunching(application: UIApplication?, launchOptions: UIApplicationLaunchOptions?): Boolean {
        ChatService(PORT).start()
        return true
    }

    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val pool = NSAutoreleasePool()
            try {
                UIApplication.main<UIApplication, ChatterApp>(args, null, ChatterApp::class.java);
            } finally {
                pool.close()
            }
        }
    }
}
