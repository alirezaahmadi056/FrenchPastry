package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserInfoData
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelProfileFragment
import info.alirezaahmadi.frenchpastry.mvp.view.ViewProfileFragment

class PresenterProfileFragment(
    private val view: ViewProfileFragment,
    val model: ModelProfileFragment,
    private val context: Context
) : BaseLifeCycle, ActivityUtils {

    override fun onCreate() {

        onClickHandler()
        view.startGetData()

        if (NetworkInfo.internetInfo(context, this))
            getUserInfo()

    }

    override fun activeNetwork() {
        getUserInfo()
    }

    private fun onClickHandler() {
        view.onClick()
    }

    private fun getUserInfo() {

        model.getUserInfo(

            object : CallbackRequest<UserInfoData> {

                override fun onSuccess(code: Int, data: UserInfoData) {
                    view.endGetData()
                    view.setUserData(data.user)
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