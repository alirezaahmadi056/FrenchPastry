package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.StartSetUserInfo
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserData
import info.alirezaahmadi.frenchpastry.databinding.ActivityUserBinding

class ViewUserActivity: FrameLayout {

    private lateinit var actUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        activityUtils: ActivityUtils
    ) : super(contextInstance) {
        actUtils = activityUtils
    }

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

        binding.txtDay.setText(userData.day)
        binding.txtMonth.setText(userData.month)
        binding.txtYear.setText(userData.year)

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

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun onBack() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            actUtils.finished()
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