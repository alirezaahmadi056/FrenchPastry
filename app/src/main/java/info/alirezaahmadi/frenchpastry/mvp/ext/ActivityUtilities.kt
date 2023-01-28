package info.alirezaahmadi.frenchpastry.mvp.ext

import androidx.fragment.app.Fragment

interface ActivityUtils {

    fun setFragment(fragment: Fragment) {}

    fun finished() {}

    fun finishedAffinity() {}

}