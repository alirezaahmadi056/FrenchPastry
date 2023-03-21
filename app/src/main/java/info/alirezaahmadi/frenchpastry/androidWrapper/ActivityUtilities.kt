package info.alirezaahmadi.frenchpastry.androidWrapper

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.SliderModel

interface ActivityUtils {

    fun setFragment(fragment: Fragment) {}

    fun setViewPagerFragment(viewPager: ViewPager2, data: ArrayList<SliderModel>) {}

    fun finished() {}

    fun finishedAffinity() {}

    fun showToast() {}

    fun showSnackBar() {}

    fun activeNetwork() {}

}