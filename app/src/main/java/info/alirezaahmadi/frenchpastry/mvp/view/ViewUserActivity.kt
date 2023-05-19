package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserData
import info.alirezaahmadi.frenchpastry.databinding.ActivityUserBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

class ViewUserActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding = ActivityUserBinding.inflate(
        LayoutInflater.from(context)
    )

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

        binding.txtEditName.setText(userData.fullname)
        binding.txtEmail.setText(userData.email)

        //todo در اینجا باید روز و سال و ماه را اضافه کنیم به دیتا خودمان و بریزیم توش
        //binding.txtDay.setText()
        //binding.txtMount.setText()
        //binding.txtYear.setText()

        //todo اینجا هم باید بریم به آدرس های من
        //binding.txtAddress.setOnClickListener {  }

        if (userData.sex == 1)
            binding.rdbMen.isChecked = true
        else
            binding.rdbWomen.isChecked = true

    }

}