package info.alirezaahmadi.frenchpastry.mvp.model

import android.content.Context
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo

class ModelLoginActivity(
    private val context: Context
) {

    fun getDeviceInfo() = DeviceInfo()

    fun getUID() = DeviceInfo.getDeviceID(context)

    fun getPublicKey() = DeviceInfo.getPublicKeyWithoutApi(context)

}