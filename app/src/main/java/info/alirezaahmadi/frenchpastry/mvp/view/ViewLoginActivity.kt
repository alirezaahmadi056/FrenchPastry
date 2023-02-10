package info.alirezaahmadi.frenchpastry.mvp.view

import android.annotation.SuppressLint
import android.app.AlertDialog
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
import info.alirezaahmadi.frenchpastry.databinding.ActivityLoginBinding
import info.alirezaahmadi.frenchpastry.databinding.CustomDialogLoginBinding
import java.math.BigInteger
import java.security.MessageDigest


class ViewLoginActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityLoginBinding.inflate(inflater)
    private var resendState = false

    fun test() {

        val input = "AlirezaAhmadi"
        val md = MessageDigest.getInstance("MD5")
        val hash = BigInteger(
            1,
            md.digest(input.toByteArray())
        ).toString(16).padStart(32, '0')

    }

    fun pressedSendCode() {

        binding.btnLogin.getView().setOnClickListener {

            val number = binding.inputTextPhone.getText()

            if (numberValidation(number)) {
                createDialog(number)
            }

        }

    }

    @SuppressLint("SetTextI18n")
    private fun createDialog(number: String) {

        val view = CustomDialogLoginBinding.inflate(inflater)

        view.txtShowNumber.text = "کد تایید به شماره $number ارسال شد"
        view.txtResend.setTextColor(Color.parseColor("#D9888383"))

        // Run Timer
        createTimer(view)

        // Config Alert Dialog
        val builder = AlertDialog.Builder(context)
            .setView(view.root)
            .setCancelable(false)

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view.btnConfirm.getView().setOnClickListener {

            val code = view.edtEnterCode.text.toString()

            if (code.length < 4) {
                view.inputEnterCode.error = "کد 4 رقمی را وارد کنید"
                return@setOnClickListener
            } else
                view.inputEnterCode.error = null

            if(confirmCode()){

                dialog.dismiss()

                val viewName = CustomDialogLoginBinding.inflate(inflater)
                val builder2 = AlertDialog.Builder(context)
                    .setView(viewName.root)
                    .setCancelable(false)

                viewName.txtResend.visibility = GONE
                viewName.txtTime.visibility = GONE
                viewName.txtEditPhone.visibility = GONE
                viewName.txtShowNumber.visibility = GONE
                viewName.edtEnterCode.inputType = InputType.TYPE_CLASS_TEXT
                viewName.textView.text = "اطلاعات کاربری"
                viewName.edtEnterCode.hint = "نام و نام خانوادگی"
                viewName.edtEnterCode.gravity = Gravity.START
                viewName.edtEnterCode.textDirection = TEXT_DIRECTION_RTL
                viewName.edtEnterCode.filters = arrayOf(InputFilter.LengthFilter(40))
                viewName.btnConfirm.getView().text = "ثبت اطلاعات"

                val dialog2 = builder2.create()
                dialog2.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog2.show()

                viewName.btnConfirm.getView().setOnClickListener {
                    val name = viewName.edtEnterCode.text.toString().trim()
                    if (name.isEmpty() || name.length < 3)
                        viewName.inputEnterCode.error = "لطفا نام خود را وارد کنید"
                    else
                        viewName.inputEnterCode.error = null
                    Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
                }

            }

        }

        view.txtEditPhone.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun confirmCode(): Boolean {

        return true
    }

    private fun createTimer(view: CustomDialogLoginBinding) {

        object : CountDownTimer(60000, 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                view.txtTime.text = "00:${millisUntilFinished / 1000}"
            }

            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                view.txtTime.text = "00:00"
                resendState = true
                view.txtResend.setTextColor(Color.parseColor("#101219"))
                view.txtResend.setOnClickListener {
                    if (resendState) {
                        view.txtResend.setTextColor(Color.parseColor("#D9888383"))
                        resendState = false
                        createTimer(view)
                    }
                }
            }

        }.start()

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

}