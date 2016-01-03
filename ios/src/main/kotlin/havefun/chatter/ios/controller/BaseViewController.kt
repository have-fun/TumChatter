package havefun.chatter.ios.controller

import havefun.chatter.core.domain.Error
import havefun.chatter.core.presenter.PresenterFactory
import havefun.chatter.core.view.BaseView
import havefun.chatter.ios.ChatterApp
import havefun.chatter.ios.injection.AppComponent
import havefun.chatter.ios.resource.StringRes.Companion.BUTTON_OK
import havefun.chatter.ios.resource.StringRes.Companion.ERROR_GENERAL
import havefun.chatter.ios.resource.StringRes.Companion.ERROR_TITLE
import org.robovm.apple.dispatch.DispatchQueue
import org.robovm.apple.foundation.NSString
import org.robovm.apple.uikit.*
import javax.inject.Inject

abstract class BaseViewController : UIViewController(), BaseView {
    @Inject lateinit var presenterFactory: PresenterFactory

    override val isActive: Boolean
        get() = view != null

    abstract fun inject(component: AppComponent)

    override fun runOnUiThread(action: () -> Unit) {
        DispatchQueue.getMainQueue().sync(action);
    }

    override fun viewDidLoad() {
        super.viewDidLoad()

        val chatterApp = UIApplication.getSharedApplication().delegate as ChatterApp
        inject(chatterApp.appComponent)

        presenter.onLoaded()
    }

    override fun viewWillAppear(animated: Boolean) {
        super.viewWillAppear(animated)

        presenter.onWillAppear()
    }

    override fun viewWillDisappear(animated: Boolean) {
        super.viewWillDisappear(animated)

        presenter.onWillDisappear()
    }

    override fun viewDidDisappear(animated: Boolean) {
        super.viewDidDisappear(animated)
    }

    override fun doDispose() {
        super.doDispose()

        presenter.onUnloaded()
    }

    override fun showLoading() {
    }

    override fun showError(error: Error) {
        val title = NSString.getLocalizedString(ERROR_TITLE)
        val message = if (error.level == Error.SeverityLevel.SYSTEM) NSString.getLocalizedString(ERROR_GENERAL) else error.message
        val button = NSString.getLocalizedString(BUTTON_OK)

        val alert = UIAlertController(title, message, UIAlertControllerStyle.Alert)
        alert.addAction(UIAlertAction(button, UIAlertActionStyle.Default, null))

        presentViewController(alert, true, {})
    }
}