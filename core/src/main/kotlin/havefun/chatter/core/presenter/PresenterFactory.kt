package havefun.chatter.core.presenter

import havefun.chatter.core.injection.CoreComponent
import havefun.chatter.core.presenter.BasePresenter
import havefun.chatter.core.view.BaseView
import java.util.*

open class PresenterFactory(val component: CoreComponent) {
    open val cache: MutableMap<String, BasePresenter<*>?> = WeakHashMap<String, BasePresenter<*>?>()

    inline fun <reified PRESENTER, VIEW> presenter(constructor: (CoreComponent, VIEW) -> PRESENTER, view: VIEW) : PRESENTER
            where PRESENTER : BasePresenter<*>, VIEW : BaseView  {

        val key = PRESENTER::class.java.simpleName
        var presenter = cache[key] as PRESENTER?

        if (presenter == null) {
            presenter = constructor(component, view)
            cache[key] = presenter
        }

        return presenter
    }
}