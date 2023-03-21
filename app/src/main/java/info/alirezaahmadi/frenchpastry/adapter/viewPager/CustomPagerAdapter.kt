package info.alirezaahmadi.frenchpastry.adapter.viewPager

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.SliderModel
import info.alirezaahmadi.frenchpastry.ui.fragment.MainSliderFragment

class CustomSliderPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val data: ArrayList<SliderModel>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = data.size

    override fun createFragment(position: Int) = MainSliderFragment(data[position].small)

}