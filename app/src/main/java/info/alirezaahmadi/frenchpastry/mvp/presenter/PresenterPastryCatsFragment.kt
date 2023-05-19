package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ParentCategoryModel
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelPastryCatsFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewPastryCatsFragment

class PresenterPastryCatsFragment(
    private val view: ViewPastryCatsFragment,
    val model: ModelPastryCatsFragment,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {

        view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            getCats()

    }

    override fun activeNetwork() {
        getCats()
    }

    private fun getCats() {

        model.getCats(

            object : CallbackRequest<ParentCategoryModel> {

                override fun onSuccess(code: Int, data: ParentCategoryModel) {
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