package testsupport

import havefun.chatter.core.domain.Error
import havefun.chatter.core.presenter.BasePresenter
import havefun.chatter.core.view.BaseView

open class MockBaseView : BaseView {
    override val isActive: Boolean
        get() = true

    override val presenter: BasePresenter<*>
        get() = throw UnsupportedOperationException()

    override fun runOnUiThread(action: () -> Unit) {
        action()
    }

    override fun showLoading() {
    }

    override fun showError(error: Error) {
    }
}