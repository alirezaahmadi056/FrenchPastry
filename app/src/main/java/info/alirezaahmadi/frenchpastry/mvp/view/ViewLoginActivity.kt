package info.alirezaahmadi.frenchpastry.mvp.view

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import info.alirezaahmadi.frenchpastry.databinding.ActivityLoginBinding
import info.alirezaahmadi.frenchpastry.databinding.CustomDialogLoginBinding


class ViewLoginActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityLoginBinding.inflate(inflater)

    fun test() {

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
        createTimer(view)
        val builder = AlertDialog.Builder(context)
            .setView(view.root)
            .setCancelable(false)

        view.btnConfirm.setOnClickListener {
            //check code
        }

        view.txtResend.setOnClickListener {
            //resend code
        }

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()

        view.txtEditPhone.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun createTimer(view: CustomDialogLoginBinding) {

        object : CountDownTimer(60000, 1000) {

            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                view.txtTime.text = "00:${millisUntilFinished / 1000}"
            }

            override fun onFinish() {

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

        return true

    }

}