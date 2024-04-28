package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserData
import info.alirezaahmadi.frenchpastry.databinding.FragmentProfileBinding
import info.alirezaahmadi.frenchpastry.ui.activity.AddressActivity
import info.alirezaahmadi.frenchpastry.ui.activity.CustomCakeActivity
import info.alirezaahmadi.frenchpastry.ui.activity.FavoriteActivity
import info.alirezaahmadi.frenchpastry.ui.activity.UserActivity

class ViewProfileFragment(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        FragmentProfileBinding.inflate(LayoutInflater.from(context))

    fun startGetData() {
        binding.mainView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.mainView.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun setUserData(userData: UserData) {

        binding.txtName.text = userData.fullname
        binding.txtPhone.text = userData.phone

    }

    fun onClick() {

        binding.imgUserInfo.setOnClickListener {
            context.startActivity(Intent(context, UserActivity::class.java))
        }

        binding.imgFavorite.setOnClickListener {
            context.startActivity(Intent(context, FavoriteActivity::class.java))
        }

        binding.imgAddress.setOnClickListener {
            context.startActivity(Intent(context, AddressActivity::class.java))
        }

        binding.imgCake.setOnClickListener {
            context.startActivity(Intent(context, CustomCakeActivity::class.java))
        }

    }

}