package info.alirezaahmadi.frenchpastry.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.androidWrapper.PicassoHandler
import info.alirezaahmadi.frenchpastry.databinding.FragmentMainImageSliderBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.OthersUtilities

class MainSliderFragment(
    private val url: String,
    private val count: ArrayList<Int>,
    private val position: Int
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMainImageSliderBinding.inflate(inflater)

        if (count.size > 1) {

            count.forEach {

                val view = View(context)
                val size = OthersUtilities.getPixel(10f, resources)
                val param = ViewGroup.MarginLayoutParams(size, size)
                param.marginEnd = OthersUtilities.getPixel(8f, resources)
                view.layoutParams = param

                if (it == position)
                    view.setBackgroundResource(R.drawable.back_slider_count_enable)
                else
                    view.setBackgroundResource(R.drawable.back_slider_count_disable)

                binding.linearLayoutSliderCount.addView(view)

            }

        } else
            binding.linearLayoutSliderCount.visibility = View.INVISIBLE

        PicassoHandler.setPicasso(binding.imgSlider, url)

        return binding.root

    }

}