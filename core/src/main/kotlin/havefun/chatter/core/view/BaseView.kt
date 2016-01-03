package havefun.chatter.core.view

import havefun.chatter.core.domain.Error
import havefun.chatter.core.presenter.BasePresenter

interface BaseView {
    val isActive: Boolean
    val presenter: BasePresenter<*>

    fun runOnUiThread(action: () -> Unit)
    fun showLoading()
    fun showError(error: Error)
}