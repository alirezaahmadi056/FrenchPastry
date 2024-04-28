package info.alirezaahmadi.frenchpastry.androidWrapper

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.provider.Settings
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.databinding.CustomDialogInternetBinding

object NetworkInfo {

    fun internetInfo(context: Context, activityUtils: ActivityUtils): Boolean {

        return if (netInfo(context))
            true
        else {
            showNetDialog(context, activityUtils)
            false
        }

    }

    private fun internetInfoRetry(context: Context, activityUtils: ActivityUtils): Boolean {

        return if (netInfo(context)) {
            activityUtils.activeNetwork()
            true
        } else {
            showNetDialog(context, activityUtils)
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

    private fun showNetDialog(context: Context, activityUtils: ActivityUtils) {

        val view5 = CustomDialogInternetBinding.inflate(LayoutInflater.from(context))
        val dialog = Dialog(context)
        dialog.setContentView(view5.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view5.btnRetry.setOnClickListener {
            dialog.dismiss()
            internetInfoRetry(context, activityUtils)
        }

        view5.btnSettings.setOnClickListener {
            context.startActivity(Intent(Settings.ACTION_SETTINGS))
        }

    }

}