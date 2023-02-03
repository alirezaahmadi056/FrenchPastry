package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.databinding.ActivityLoginBinding

class ViewLoginActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding = ActivityLoginBinding.inflate(LayoutInflater.from(context))

    fun test() {

    }

}