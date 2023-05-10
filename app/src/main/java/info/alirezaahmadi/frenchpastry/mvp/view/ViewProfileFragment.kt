package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserData
import info.alirezaahmadi.frenchpastry.databinding.FragmentProfileBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.ui.activity.UserActivity

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

    fun setUserData(userData: UserData) {

        binding.txtName.text = userData.fullname
        binding.txtPhone.text = userData.phone

    }

    fun onClick() {

        binding.imgUserInfo.setOnClickListener {
            context.startActivity(Intent(context, UserActivity::class.java))
        }

    }

    fun toast() {

        ToastUtils.toastServerError(context)

    }

}