package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryMainModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelDetailPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewDetailPastryActivity

class PresenterDetailPastryActivity(
    private val view: ViewDetailPastryActivity,
    private val model: ModelDetailPastryActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

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

        model.getCats(

            object : CallbackRequest<PastryMainModel> {

                override fun onSuccess(code: Int, data: PastryMainModel) {
                    view.endGetData()
                    view.setData(data.pastry)
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

        )

    }

}