package info.alirezaahmadi.frenchpastry.ui.fragment

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.databinding.FragmentMainImageSliderBinding

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

        count.forEach {

            val view = View(context)
            val param = ViewGroup.MarginLayoutParams(test(10f), test(10f))
            param.marginEnd = test(8f)
            view.layoutParams = param

            if (it == position)
                view.setBackgroundResource(R.drawable.back_slider_count_enable)
            else
                view.setBackgroundResource(R.drawable.back_slider_count_disable)

            binding.linearLayoutSliderCount.addView(view)

        }

        //TODO place holder image low quality (change place holder image)
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.img_place_holder)
            .error(R.drawable.img_place_holder)
            .fit()
            .into(binding.imgSlider)

        return binding.root

    }

    fun test(dip: Float): Int {

        val r: Resources = resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dip,
            r.displayMetrics
        ).toInt()

    }

}