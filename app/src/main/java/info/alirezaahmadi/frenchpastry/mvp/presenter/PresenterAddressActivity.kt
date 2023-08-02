package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Address
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelAddressActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewAddressActivity

class PresenterAddressActivity(
    private val view: ViewAddressActivity,
    private val model: ModelAddressActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {

        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getAddress()

    }

    override fun onStart() {
        getAddress()
    }

    override fun activeNetwork() {
        getAddress()
    }

    private fun getAddress() {

        model.getAddress(
            DeviceInfo.getApi(context),
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),

            object : CallbackRequest<Address> {

                override fun onSuccess(code: Int, data: Address) {
                    view.endGetData()
                    view.setDataRecycler(data)
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