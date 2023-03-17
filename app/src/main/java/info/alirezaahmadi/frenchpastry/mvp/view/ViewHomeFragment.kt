package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.databinding.FragmentHomeBinding

class ViewHomeFragment(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        FragmentHomeBinding.inflate(LayoutInflater.from(context))

    fun initSlider(){

    }

}