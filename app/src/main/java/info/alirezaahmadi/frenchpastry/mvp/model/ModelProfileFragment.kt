package info.alirezaahmadi.frenchpastry.mvp.model

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.UserApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserInfoData
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest

class ModelProfileFragment(private val context: Context) {

    fun getUserInfo(callbackRequest: CallbackRequest<UserInfoData>) {
        UserApiRepository.instance.getUserInfo(
            DeviceInfo.getApi(context),
            DeviceInfo.getDeviceID(context),
            DeviceInfo.getPublicKey(context),
            callbackRequest
        )
    }

}