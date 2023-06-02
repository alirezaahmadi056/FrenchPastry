package info.alirezaahmadi.frenchpastry.mvp.model

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.UserApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.DefaultModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserInfoData
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelUserActivity(private val context: Context) {

    fun getUserInfo(callbackRequest: CallbackRequest<UserInfoData>) {
        UserApiRepository.instance.getUserInfo(
            DeviceInfo.getApi(context),
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),
            callbackRequest
        )
    }

    fun setUserInfo(
        name: String,
        email: String,
        day: String,
        month: String,
        year: String,
        sex: Int,
        callbackRequest: CallbackRequest<DefaultModel>
    ) {
        UserApiRepository.instance.setUserInfo(
            DeviceInfo.getApi(context),
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),
            name, email, day, month, year, sex,
            callbackRequest
        )
    }

}