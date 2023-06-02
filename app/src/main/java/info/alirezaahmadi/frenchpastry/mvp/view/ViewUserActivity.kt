package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.StartSetUserInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserData
import info.alirezaahmadi.frenchpastry.databinding.ActivityUserBinding

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

    fun setUserData(userData: UserData, setUserInfo: StartSetUserInfo) {

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

        when (userData.sex) {
            0 -> binding.rdbWomen.isChecked = true
            1 -> binding.rdbMen.isChecked = true
            else -> binding.rdbHide.isChecked = true
        }

        binding.btnSaveInfo.getView().setOnClickListener {

            binding.btnSaveInfo.enableProgress()

            val emptyError = "لطفا این فیلد را پر کنید"
            val name = binding.txtEditName.getText().trim()
            val email = binding.txtEmail.getText().trim()
            val day = binding.txtDay.getText().trim()
            val month = binding.txtMonth.getText().trim()
            val year = binding.txtYear.getText().trim()

            val sex =
                when (binding.rdg.checkedRadioButtonId) {
                    R.id.rdbMen -> 1
                    R.id.rdbWomen -> 0
                    else -> 2
                }

            if (name.isEmpty()) {
                binding.txtEditName.setError(emptyError)
                binding.btnSaveInfo.disableProgress()
                return@setOnClickListener
            } else
                binding.txtEditName.nullError()

            if (email.isEmpty()) {
                binding.txtEmail.setError(emptyError)
                binding.btnSaveInfo.disableProgress()
                return@setOnClickListener
            } else
                binding.txtEmail.nullError()

            setUserInfo.startSetUser(name, email, day, month, year, sex)

        }

    }

    fun endSetUserInfoSuccess() {
        binding.btnSaveInfo.disableProgress()
        binding.txtName.text = binding.txtEditName.getText()
    }

    fun endSetUserInfoNotSuccess() {
        binding.btnSaveInfo.disableProgress()
    }

}