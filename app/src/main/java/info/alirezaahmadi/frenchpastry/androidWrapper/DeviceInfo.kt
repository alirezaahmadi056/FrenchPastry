package info.alirezaahmadi.frenchpastry.androidWrapper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.window.layout.WindowMetricsCalculator
import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.math.BigInteger
import java.security.MessageDigest

class DeviceInfo {

    companion object {

        @SuppressLint("HardwareIds")
        fun getDeviceID(context: Context): String = Settings.Secure.getString(
            context.contentResolver,
            Settings.Secure.ANDROID_ID
        ) ?: ""

        fun getPublicKey(context: Context): String {

            val privateKey = "NKFewSfScCRrbxzULrSivWAXq2yvGd"

            val input = privateKey + getDeviceID(context)
            val md = MessageDigest.getInstance("MD5")

            return BigInteger(
                1,
                md.digest(input.toByteArray())
            ).toString(16).padStart(32, '0')

        }

    }

    val getDeviceName = "${Build.BRAND} | ${Build.MODEL}"

    val getAndroidVersion = "API Level : ${Build.VERSION.SDK_INT} | V : ${Build.VERSION.RELEASE}"

    fun getScreenSize(activity: Activity): String {

        val windowMetrics =
            WindowMetricsCalculator.getOrCreate().computeCurrentWindowMetrics(activity)

        return "${windowMetrics.bounds.width()} * ${windowMetrics.bounds.height()}"

    }

    val isDeviceRooted: Boolean
        get() = checkRootMethod1() || checkRootMethod2() || checkRootMethod3()

    private fun checkRootMethod1(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootMethod2(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )
        for (path in paths) {
            if (File(path).exists()) return true
        }
        return false
    }

    private fun checkRootMethod3(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            val `in` = BufferedReader(InputStreamReader(process.inputStream))
            `in`.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }

}