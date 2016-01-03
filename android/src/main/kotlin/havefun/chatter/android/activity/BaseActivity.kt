package havefun.chatter.android.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import butterknife.ButterKnife
import havefun.chatter.android.ChatterApp
import havefun.chatter.android.R
import havefun.chatter.android.injection.AppComponent
import havefun.chatter.core.domain.Error
import havefun.chatter.core.domain.Error.SeverityLevel.SYSTEM
import havefun.chatter.core.presenter.PresenterFactory
import havefun.chatter.core.view.BaseView
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BaseView {

    @Inject lateinit var presenterFactory: PresenterFactory
    private var isPaused = true

    override val isActive: Boolean
        get() = !isPaused

    abstract fun inject(component: AppComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject((application as ChatterApp).appComponent)

        isPaused = false
        presenter.onLoaded()

        if (savedInstanceState != null) {
            val state = savedInstanceState.getSerializable(STATE_MAP) as Map<String, Any?>
            presenter.onRestoreState(state)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        presenter.onUnloaded()
    }

    override fun onResume() {
        super.onResume()

        presenter.onWillAppear()
    }

    override fun onPause() {
        isPaused = true
        super.onPause()

        presenter.onWillDisappear()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)

        val myState = hashMapOf<String, Any?>()
        presenter.onSaveState(myState)

        outState!!.putSerializable(STATE_MAP, myState)
    }

    override fun setContentView(layoutResID: Int) {
        super.setContentView(layoutResID)

        ButterKnife.bind(this)
    }

    override fun runOnUiThread(action: () -> Unit) {
        super.runOnUiThread(Runnable { action() })
    }

    override fun showLoading() {
    }

    override fun showError(error: Error) {
        val message = if (error.level == SYSTEM) getString(R.string.error_general) else error.message
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    companion object {
        private const val STATE_MAP = "stateMap"
    }
}