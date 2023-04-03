package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.RequestMain
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelHomeFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewHomeFragment

class PresenterHomeFragment(
    private val view: ViewHomeFragment,
    private val model: ModelHomeFragment,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {
        createSlider()
    }

    private fun createSlider() {

        view.setFaceData(model.faceData[0])

        if (NetworkInfo.internetInfo(context, this))
            sendRequest()

    }

    override fun activeNetwork() {
        sendRequest()
    }

    private fun sendRequest() {

        model.getContent(
            object : CallbackRequest<RequestMain> {

                override fun onSuccess(code: Int, data: RequestMain) {
                    view.initialized(data)
                }

                override fun onNotSuccess(code: Int, error: String, message: String) {
                    view.toast(message, false)
                }

                override fun onError(error: String) {
                    view.toast("", true)
                }

            }
        )

    }

}