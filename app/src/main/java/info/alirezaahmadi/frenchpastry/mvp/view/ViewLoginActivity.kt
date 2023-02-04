package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import info.alirezaahmadi.frenchpastry.databinding.ActivityLoginBinding

class ViewLoginActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding = ActivityLoginBinding.inflate(LayoutInflater.from(context))

    fun test() {
        binding.btnLogin.setOnClickListener {

            val number = binding.inputTextPhone.getText()
            val check = number.matches(Regex("(\\+98|0)?9\\d{9}"))

            Toast.makeText(context, "test true...", Toast.LENGTH_SHORT).show()
            Snackbar.make(binding.root, "$check", Snackbar.LENGTH_SHORT).show()

        }
    }

    fun pressedSendCode() {

        binding.btnLogin.setOnClickListener {

            val number = binding.inputTextPhone.getText()

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

        val test = number.matches(Regex("(\\+98|0)?9\\d{9}"))

        return true

    }

}