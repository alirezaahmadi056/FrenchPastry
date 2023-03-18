package info.alirezaahmadi.frenchpastry.androidWrapper

import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

interface ActivityUtils {

    fun setFragment(fragment: Fragment) {}

    fun setViewPagerFragment(viewPager: ViewPager2, url: ArrayList<String>) {}

    fun finished() {}

    fun finishedAffinity() {}

    fun showToast() {}

    fun showSnackBar() {}

    fun activeNetwork() {}

}