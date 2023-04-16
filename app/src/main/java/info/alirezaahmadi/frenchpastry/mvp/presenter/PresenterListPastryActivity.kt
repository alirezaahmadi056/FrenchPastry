package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.model.ModelListPastryActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewListPastryActivity

class PresenterListPastryActivity(
    private val view: ViewListPastryActivity,
    private val model: ModelListPastryActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {

        onBackClick()

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
                    view.setData(data.pastries)
                }

                override fun onNotSuccess(code: Int, error: String, message: String) {

                }

                override fun onError(error: String) {

                }

            }

        )

    }

}