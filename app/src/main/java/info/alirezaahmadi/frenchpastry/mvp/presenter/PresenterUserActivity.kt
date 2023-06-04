package info.alirezaahmadi.frenchpastry.mvp.presenter

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.StartSetUserInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserInfoData
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.mvp.ext.BaseLifeCycle
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.mvp.model.ModelUserActivity
import info.alirezaahmadi.frenchpastry.mvp.view.ViewUserActivity

class PresenterUserActivity(
    private val view: ViewUserActivity,
    private val model: ModelUserActivity,
    private val context: Context
) : BaseLifeCycle, ActivityUtils, StartSetUserInfo {

    override fun onCreate() {

        view.startGetData()
        view.showNavDrawer()
        view.onBack()

        if (NetworkInfo.internetInfo(context, this))
            getUserInfo()

    }

    override fun activeNetwork() {
        getUserInfo()
    }

    private fun getUserInfo() {

        model.getUserInfo(

            object : CallbackRequest<UserInfoData> {

                override fun onSuccess(code: Int, data: UserInfoData) {
                    view.endGetData()
                    view.setUserData(data.user, this@PresenterUserActivity)
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

    override fun startSetUser(
        name: String,
        email: String,
        day: String,
        month: String,
        year: String,
        sex: Int
    ) {

        model.setUserInfo(
            name, email, day, month, year, sex,

            object : CallbackRequest<DefaultModel> {

                override fun onSuccess(code: Int, data: DefaultModel) {
                    view.endSetUserInfoSuccess()
                    ToastUtils.toast(context, data.message)
                }

                override fun onNotSuccess(code: Int, error: String) {
                    view.endSetUserInfoNotSuccess()
                    ToastUtils.toast(context, error)
                }

                override fun onError(error: String) {
                    ToastUtils.toastServerError(context)
                }

            }

        )

    }

}