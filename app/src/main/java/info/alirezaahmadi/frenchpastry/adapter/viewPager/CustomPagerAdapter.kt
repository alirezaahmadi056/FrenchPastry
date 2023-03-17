package info.alirezaahmadi.frenchpastry.adapter.viewPager

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import info.alirezaahmadi.frenchpastry.ui.fragment.MainSliderFragment

class CustomSliderPagerAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val data: ArrayList<String>
) : FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount() = 3

    override fun createFragment(position: Int) = MainSliderFragment(data[position])

}