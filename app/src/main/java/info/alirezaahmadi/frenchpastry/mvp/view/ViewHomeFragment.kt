package info.alirezaahmadi.frenchpastry.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.RequestMain
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.SliderModel
import info.alirezaahmadi.frenchpastry.databinding.FragmentHomeBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

@SuppressLint("ViewConstructor")
class ViewHomeFragment(
    contextInstance: Context,
    private val activityUtils: ActivityUtils
) : FrameLayout(contextInstance) {

    val binding =
        FragmentHomeBinding.inflate(LayoutInflater.from(context))

    fun initialized(data: RequestMain) {

        binding.sliderViewPager.layoutDirection = View.LAYOUT_DIRECTION_RTL

        activityUtils.setViewPagerFragment(binding.sliderViewPager, data.sliders)

    }

    fun setViewPagerFaceData(data: ArrayList<SliderModel>) {

        binding.sliderViewPager.layoutDirection = View.LAYOUT_DIRECTION_RTL

        activityUtils.setViewPagerFragment(binding.sliderViewPager, data)

    }

    fun toast(text: String, errorState: Boolean) {

        if (errorState)
            ToastUtils.toastServerError(context)
        else
            ToastUtils.toast(context, text)

    }

}