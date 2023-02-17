package info.alirezaahmadi.frenchpastry.androidWrapper

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.provider.Settings
import androidx.window.layout.WindowMetricsCalculator

class DeviceInfo(private val context: Activity) {

    val getPhoneName = "${Build.BRAND} | ${Build.MODEL}"

    @SuppressLint("HardwareIds")
    val getDeviceID = Settings.Secure.getString(
        context.contentResolver,
        Settings.Secure.ANDROID_ID
    )

    val getSDK = "${Build.VERSION.SDK_INT} | V : ${Build.VERSION.RELEASE}"

    private val windowMetrics = WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(
        context
    )
    private val currentBounds = windowMetrics.bounds

    private val getWidth = currentBounds.width()
    private val getHeight = currentBounds.height()

    fun getScreenSize() = "$getWidth * $getHeight"

}