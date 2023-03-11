package info.alirezaahmadi.frenchpastry.androidWrapper

import androidx.fragment.app.Fragment

interface ActivityUtils {

    fun setFragment(fragment: Fragment) {}

    fun finished() {}

    fun finishedAffinity() {}

    fun showToast(){}

    fun showSnackBar(){}

    fun activeNetwork(){}

}