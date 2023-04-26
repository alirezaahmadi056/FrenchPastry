package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.databinding.FragmentProfileBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

class ViewProfileFragment(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        FragmentProfileBinding.inflate(LayoutInflater.from(context))

    fun startGetData() {
    }

    fun endGetData() {
    }

    fun endProgress() {
    }

    fun toast(text: String, errorState: Boolean) {

        if (errorState)
            ToastUtils.toastServerError(context)
        else
            ToastUtils.toast(context, text)

    }

}