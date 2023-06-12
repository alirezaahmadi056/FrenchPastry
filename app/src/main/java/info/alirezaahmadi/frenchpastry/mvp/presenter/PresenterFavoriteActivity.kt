package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.AllPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelFavoriteActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewFavoriteActivity

class PresenterFavoriteActivity(
    private val view: ViewFavoriteActivity,
    private val model: ModelFavoriteActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {

        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getPastries()

    }

    override fun onStart() {

        view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            getPastries()

    }

    override fun activeNetwork() {
        getPastries()
    }

    private fun getPastries() {

        model.getPastries(

            DeviceInfo.getApi(context),
            DeviceInfo.getPublicKey(context),
            DeviceInfo.getDeviceID(context),
            object : CallbackRequest<AllPastriesModel> {

                override fun onSuccess(code: Int, data: AllPastriesModel) {
                    view.endGetData()
                    view.setDataRecycler(data.pastries)
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