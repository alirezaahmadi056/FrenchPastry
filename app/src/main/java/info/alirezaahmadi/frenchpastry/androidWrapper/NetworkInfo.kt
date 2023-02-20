package info.alirezaahmadi.frenchpastry.androidWrapper

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

object NetworkInfo {

    fun internetInfo(context: Context): Boolean {

        return if (netInfo(context))
            true
        else {
            showNetDialog(context)
            false
        }

    }

    private fun netInfo(context: Context): Boolean {

        val connectivityManager =
            context.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

    }

    private fun showNetDialog(context: Context) {

        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("خطا")
        dialog.setCancelable(false)
        dialog.setMessage("لطفا گوشی خود را به اینترنت متصل کنید")

        dialog.setPositiveButton("تلاش مجدد") { view, _ ->
            view.dismiss()
            internetInfo(context)
        }

        dialog.setNeutralButton("تنظیمات") { _, _ ->
            context.startActivity(Intent(Settings.ACTION_SETTINGS))
        }

        dialog.create().show()

    }

}