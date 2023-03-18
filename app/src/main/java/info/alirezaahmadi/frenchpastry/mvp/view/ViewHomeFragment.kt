package info.alirezaahmadi.frenchpastry.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.databinding.FragmentHomeBinding

@SuppressLint("ViewConstructor")
class ViewHomeFragment(
    contextInstance: Context,
    private val activityUtils: ActivityUtils
) : FrameLayout(contextInstance) {

    val binding =
        FragmentHomeBinding.inflate(LayoutInflater.from(context))

    fun initSlider(url: ArrayList<String>) {
        activityUtils.setViewPagerFragment(binding.sliderViewPager, url)
    }

}