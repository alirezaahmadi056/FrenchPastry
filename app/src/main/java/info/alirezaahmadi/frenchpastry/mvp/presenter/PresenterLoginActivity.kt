package info.alirezaahmadi.frenchpastry.mvp.presenter

import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelLoginActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewLoginActivity

class PresenterLoginActivity(
    private val view: ViewLoginActivity,
    private val model: ModelLoginActivity
) : BaseLifeCycle {

    override fun onCreate() {
        sendDeviceInfo()
        onClickSendCode()
    }

    private fun onClickSendCode() {
        view.pressedSendCode(model.getUID(), model.getPublicKey())
    }

    private fun sendDeviceInfo() {
        view.setDeviceInfo(model.getDeviceInfo())
    }

}