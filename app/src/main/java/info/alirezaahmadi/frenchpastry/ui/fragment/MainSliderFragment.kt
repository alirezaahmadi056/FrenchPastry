package info.alirezaahmadi.frenchpastry.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.databinding.FragmentMainImageSliderBinding

class MainSliderFragment(private val url: String) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentMainImageSliderBinding.inflate(inflater)

        //TODO place holder image low quality (change place holder image)
        Picasso.get()
            .load(url)
            .placeholder(R.drawable.img_place_holder)
            .error(R.drawable.img_place_holder)
            .fit()
            .into(binding.imgSlider)

        return binding.root

    }

}