package info.alirezaahmadi.frenchpastry.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.UserApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.UserInfoData
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.databinding.ActivityNavDrawerBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

class NavDrawerActivity : AppCompatActivity(), OnClickListener {

    private lateinit var binding: ActivityNavDrawerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out)

        binding.txtOrders.setOnClickListener(this)
        binding.txtAbout.setOnClickListener(this)
        binding.txtContact.setOnClickListener(this)
        binding.txtSupport.setOnClickListener(this)
        binding.txtUpgrade.setOnClickListener(this)
        binding.txtLogout.setOnClickListener(this)
        binding.imgCloseNav.setOnClickListener(this)

        onBack()

        getUserInfo()

    }

    private fun getUserInfo() {

        UserApiRepository.instance.getUserInfo(
            DeviceInfo.getApi(this),
            DeviceInfo.getDeviceID(this),
            DeviceInfo.getPublicKey(this),
            object : CallbackRequest<UserInfoData> {

                override fun onSuccess(code: Int, data: UserInfoData) {
                    binding.txtName.text = data.user.fullname
                    binding.txtPhone.text = data.user.phone
                }

                override fun onNotSuccess(code: Int, error: String) {
                    ToastUtils.toast(this@NavDrawerActivity, error)
                }

                override fun onError(error: String) {
                    ToastUtils.toastServerError(this@NavDrawerActivity)
                }

            }
        )

    }

    private fun onBack() {

        onBackPressedDispatcher.addCallback(
            this /* lifecycle owner */,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    // Back is pressed... Finishing the activity
                    finish()
                    overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
                }
            })

    }

    override fun onClick(view: View) {

        when (view.id) {

            R.id.txtOrders -> {

            }

            R.id.txtSupport -> {

            }

            R.id.txtAbout -> {
                startActivity(Intent(this, AboutActivity::class.java))
                finish()
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
            }

            R.id.txtContact -> {

            }

            R.id.txtUpgrade -> {

            }

            R.id.txtLogout -> {
                finishAffinity()
            }

            R.id.imgCloseNav -> {
                finish()
                overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out)
            }

        }

    }

}