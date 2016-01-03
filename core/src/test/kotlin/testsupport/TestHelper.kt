package testsupport

import havefun.chatter.core.injection.DaggerCoreComponent
import havefun.chatter.core.injection.CoreComponent
import havefun.chatter.core.injection.PresenterModule
import havefun.chatter.core.injection.ServiceModule

const val PORT = 8989

class TestHelper {
    companion object {
        val coreComponent: CoreComponent
            get() {
                return DaggerCoreComponent.builder()
                        .presenterModule(PresenterModule())
                        .serviceModule(ServiceModule("http://localhost:${PORT}"))
                        .build()
            }
    }
}