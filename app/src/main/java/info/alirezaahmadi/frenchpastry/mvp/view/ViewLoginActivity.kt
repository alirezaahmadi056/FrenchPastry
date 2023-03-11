package info.alirezaahmadi.frenchpastry.mvp.view

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.text.InputFilter
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.androidWrapper.DeviceInfo
import info.alirezaahmadi.frenchpastry.androidWrapper.NetworkInfo
import info.alirezaahmadi.frenchpastry.data.remote.apiRepository.LoginApiRepository
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestSendPhone
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestVerifyCode
import info.alirezaahmadi.frenchpastry.data.remote.ext.CallbackRequest
import info.alirezaahmadi.frenchpastry.databinding.ActivityLoginBinding
import info.alirezaahmadi.frenchpastry.databinding.CustomDialogLoginBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import java.math.BigInteger
import java.security.MessageDigest

class ViewLoginActivity(
    contextInstance: Context
) : FrameLayout(contextInstance), ActivityUtils {

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityLoginBinding.inflate(inflater)

    private lateinit var number: String
    private lateinit var deviceInfo: DeviceInfo

    private var resendState = false

    fun setDeviceInfo(info: DeviceInfo) {
        deviceInfo = info
    }

    fun pressedSendCode() {

        binding.btnLogin.getView().setOnClickListener {

            number = binding.inputTextPhone.getText()

            if (numberValidation(number)) {
                if (isCheckedNetwork()) {
                    binding.btnLogin.enableProgress()
                    LoginApiRepository.instance.sendPhoneAuth(
                        number,
                        object : CallbackRequest<RequestSendPhone> {

                            override fun onSuccess(code: Int, data: RequestSendPhone) {
                                binding.btnLogin.disableProgress()
                                createDialog()
                            }

                            override fun onError(error: String) {
                                binding.btnLogin.disableProgress()
                                ToastUtils.toastServerError(context)
                            }

                        }
                    )
                }
            }

        }

    }

    private fun numberValidation(number: String): Boolean {

        if (number.isEmpty()) {
            binding.inputTextPhone.setError("شماره خود را وارد کنید")
            return false
        }

        if (number.length < 11) {
            binding.inputTextPhone.setError("شماره را صحیح وارد کنید")
            return false
        }

        if (!number.matches(Regex("(\\+98|0)?9\\d{9}"))) {
            binding.inputTextPhone.setError("شماره را صحیح وارد کنید")
            return false
        }

        binding.inputTextPhone.setError(null)

        return true

    }

    @SuppressLint("SetTextI18n")
    private fun createDialog() {

        val view = CustomDialogLoginBinding.inflate(inflater)

        view.txtShowNumber.text = "کد تایید به $number ارسال شد"
        view.txtResend.setTextColor(Color.parseColor("#D9888383"))

        // Run Timer
        createTimer(view)

        val dialog = Dialog(context)
        dialog.setContentView(view.root)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view.btnConfirm.getView().setOnClickListener {

            val code = view.edtEnterCode.text.toString()

            if (code.length < 4) {
                view.inputEnterCode.error = "کد 4 رقمی را وارد کنید"
                return@setOnClickListener
            } else
                view.inputEnterCode.error = null

            if (isCheckedNetwork()) {

                view.btnConfirm.enableProgress()

                LoginApiRepository.instance.verifyCodeAuth(
                    code,
                    number,
                    object : CallbackRequest<RequestVerifyCode> {

                        override fun onSuccess(code: Int, data: RequestVerifyCode) {

                            view.btnConfirm.disableProgress()

                            dialog.dismiss()

                            val nameView = CustomDialogLoginBinding.inflate(inflater)
                            val nameDialog = Dialog(context)
                            nameDialog.setContentView(nameView.root)
                            nameDialog.setCancelable(false)

                            createNameView(nameView)

                            nameDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                            nameDialog.show()

                            nameView.btnConfirm.getView().setOnClickListener {

                                val name = nameView.edtEnterCode.text.toString().trim()

                                if (name.isEmpty() || name.length < 3)
                                    nameView.inputEnterCode.error = "لطفا نام خود را وارد کنید"
                                else
                                    nameView.inputEnterCode.error = null

                                if (isCheckedNetwork()) {
                                    //code
                                }

                            }

                        }

                        override fun onNotSuccess(code: Int, error: String, message: String) {
                            view.btnConfirm.disableProgress()
                            ToastUtils.toast(context, message)
                        }

                        override fun onError(error: String) {
                            view.btnConfirm.disableProgress()
                            ToastUtils.toastServerError(context)
                        }

                    }
                )

            }

        }

        view.txtEditPhone.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun createNameView(nameView: CustomDialogLoginBinding) {

        nameView.txtResend.visibility = GONE
        nameView.txtTime.visibility = GONE
        nameView.txtEditPhone.visibility = GONE
        nameView.txtShowNumber.visibility = GONE
        nameView.edtEnterCode.inputType = InputType.TYPE_CLASS_TEXT
        nameView.textView.text = "اطلاعات کاربری"
        nameView.edtEnterCode.hint = "نام و نام خانوادگی"
        nameView.edtEnterCode.gravity = Gravity.START
        nameView.edtEnterCode.textDirection = TEXT_DIRECTION_RTL
        nameView.edtEnterCode.filters = arrayOf(InputFilter.LengthFilter(40))
        nameView.btnConfirm.getView().text = "ثبت اطلاعات"

    }

    @SuppressLint("SetTextI18n")
    private fun createTimer(view: CustomDialogLoginBinding) {

        object : CountDownTimer(70000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                view.txtTime.text = "00:${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                view.txtTime.text = "00:00"
                resendState = true
                view.txtResend.setTextColor(Color.parseColor("#101219"))
                view.txtResend.setOnClickListener {
                    if (resendState) {
                        view.txtResend.setTextColor(Color.parseColor("#D9888383"))
                        resendState = false
                        LoginApiRepository.instance.sendPhoneAuth(
                            number,
                            object : CallbackRequest<RequestSendPhone> {

                                override fun onSuccess(code: Int, data: RequestSendPhone) {
                                    binding.btnLogin.disableProgress()
                                }

                                override fun onError(error: String) {
                                    binding.btnLogin.disableProgress()
                                    ToastUtils.toastServerError(context)
                                }

                            }
                        )
                        createTimer(view)
                    }
                }
            }

        }.start()

    }

    private fun isCheckedNetwork() = NetworkInfo.internetInfo(context, this)

}