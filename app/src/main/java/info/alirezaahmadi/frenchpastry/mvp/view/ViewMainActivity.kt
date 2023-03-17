package info.alirezaahmadi.frenchpastry.mvp.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.databinding.ActivityMainBinding
import info.alirezaahmadi.frenchpastry.ui.customView.bottomNav.ActiveFragment
import info.alirezaahmadi.frenchpastry.ui.customView.bottomNav.FragmentType
import info.alirezaahmadi.frenchpastry.ui.fragment.HomeFragment

@SuppressLint("ViewConstructor")
class ViewMainActivity(
    contextInstance: Context,
    private val activityUtils: ActivityUtils
) : FrameLayout(contextInstance), ActiveFragment {

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityMainBinding.inflate(inflater)

    fun initialize(){
        activityUtils.setFragment(HomeFragment(context))
    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun initBottomNav() {
        binding.bottomNav.onClickHelper(this)
    }

    override fun setFragment(type: FragmentType) {

        val fragment = when(type){
            FragmentType.HOME -> HomeFragment(context)
            FragmentType.CAKE -> Fragment()
            FragmentType.PASTRY -> Fragment()
            FragmentType.PROFILE -> Fragment()
        }

        activityUtils.setFragment(fragment)

    }

}