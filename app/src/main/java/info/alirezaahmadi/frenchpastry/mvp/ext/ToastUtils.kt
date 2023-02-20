package info.alirezaahmadi.frenchpastry.mvp.ext

import android.content.Context
import android.widget.Toast

object ToastUtils {

    fun toastServerError(context: Context) {
        toast(context, "ارتباط با سرور امکان پذیر نیست")
    }

    fun toast(context: Context, text: String) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }

}