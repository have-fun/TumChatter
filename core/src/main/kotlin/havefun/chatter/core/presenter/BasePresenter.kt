package havefun.chatter.core.presenter

import havefun.chatter.core.domain.Error
import havefun.chatter.core.domain.Error.SeverityLevel
import havefun.chatter.core.injection.CoreComponent
import havefun.chatter.core.view.BaseView
import rx.Subscriber
import java.lang.ref.WeakReference

abstract class BasePresenter<VIEW>(val component: CoreComponent, view: VIEW) where VIEW : BaseView {

    lateinit var viewWeakRef: WeakReference<VIEW>

    var view: VIEW?
        get() {
            val view = viewWeakRef.get()
            return if (view != null && view.isActive) view else null
        }
        set(value) {
            viewWeakRef = WeakReference<VIEW>(value)
        }

    init {
        viewWeakRef = WeakReference<VIEW>(view)
        inject(component)
    }

    abstract fun inject(component: CoreComponent)

    open fun onLoaded() = {}
    open fun onWillAppear() = {}
    open fun onWillDisappear() = {}
    open fun onSaveState(state: MutableMap<String, Any?>) = {}
    open fun onRestoreState(state: Map<String, Any?>) = {}
    open fun onUnloaded() = {}

    abstract class BaseSubscriber<DATA>(val baseView: BaseView) : Subscriber<DATA>() {
        override fun onCompleted() {}

        override fun onError(e: Throwable) {
            val error = Error(SeverityLevel.SYSTEM, e.message, cause = e)
            baseView.runOnUiThread { baseView.showError(error) }
        }
    }
}
