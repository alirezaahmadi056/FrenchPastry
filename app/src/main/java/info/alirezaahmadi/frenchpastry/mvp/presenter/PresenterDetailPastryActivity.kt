package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.SendRequests
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryMainModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestFavorite
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelDetailPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewDetailPastryActivity

class PresenterDetailPastryActivity(
    private val view: ViewDetailPastryActivity,
    private val model: ModelDetailPastryActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils, SendRequests {

    override fun onCreate() {

        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getDataPastry()

    }

    override fun activeNetwork() {
        getDataPastry()
    }

    private fun getDataPastry() {

        val result =
            object : CallbackRequest<PastryMainModel> {

                override fun onSuccess(code: Int, data: PastryMainModel) {
                    view.endGetData()
                    view.setData(data.pastry, this@PresenterDetailPastryActivity)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endProgress()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.endProgress()
                    ToastUtils.toastServerError(context)
                }

            }

        model.getDetailPastry(

            result,
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),
            DeviceInfo.getApi(context)

        )

    }

    override fun startSendFavorite(
        uId: String,
        pubKey: String,
        apiKey: String,
        action: String
    ) {

        val result = object : CallbackRequest<RequestFavorite> {

            override fun onSuccess(code: Int, data: RequestFavorite) {
                ToastUtils.toast(context, data.message)
            }

            override fun onNotSuccess(code: Int, error: String) {
                ToastUtils.toast(context, error)
            }

            override fun onError(error: String) {
                ToastUtils.toastServerError(context)
            }

        }

        model.setPastryFavorite(
            result,
            apiKey,
            uId,
            pubKey,
            action
        )

    }

    override fun sendComment(
        uId: String,
        pubKey: String,
        apiKey: String,
        content: String,
        rate: Float,
        postId: Int
    ) {

        model.setPastryComment(
            apiKey, uId, pubKey, postId, content, rate,

            object : CallbackRequest<DefaultModel> {

                override fun onSuccess(code: Int, data: DefaultModel) {
                    view.disableButtonProgress()
                    ToastUtils.toast(context, data.message)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.disableButtonProgress()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.disableButtonProgress()
                    ToastUtils.toastServerError(context)
                }

            }

        )

    }

}