package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.AllPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelListPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewListPastryActivity

class PresenterListPastryActivity(
    private val view: ViewListPastryActivity,
    private val model: ModelListPastryActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {
        showNavigationDrawer()
        onBackClick()
        getData()
    }

    private fun showNavigationDrawer() {
        view.showNavDrawer()
    }

    private fun getData() {

        view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            getPastries()

    }

    override fun activeNetwork() {
        getPastries()
    }

    private fun onBackClick() {
        view.onBack()
    }

    private fun getPastries() {

        model.getPastries(

            object : CallbackRequest<ListPastriesModel> {

                override fun onSuccess(code: Int, data: ListPastriesModel) {
                    view.endGetData()
                    view.setData(data)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endProgress()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    view.endProgress()
                    ToastUtils.toastServerError(context)
                }

            },

            object : CallbackRequest<AllPastriesModel> {

                override fun onSuccess(code: Int, data: AllPastriesModel) {
                    view.endGetData()
                    view.setData2(data, model.getTitle())
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