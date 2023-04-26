package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.databinding.ActivityMainBinding
import info.alirezaahmadi.frenchpastry.ui.customView.bottomNav.ActiveFragment
import info.alirezaahmadi.frenchpastry.ui.customView.bottomNav.FragmentType
import info.alirezaahmadi.frenchpastry.ui.fragment.CakeCatsFragment
import info.alirezaahmadi.frenchpastry.ui.fragment.HomeFragment
import info.alirezaahmadi.frenchpastry.ui.fragment.PastryCatsFragment
import info.alirezaahmadi.frenchpastry.ui.fragment.ProfileFragment

class ViewMainActivity : FrameLayout, ActiveFragment {

    private lateinit var activityUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        actUtils: ActivityUtils
    ) : super(contextInstance) {
        activityUtils = actUtils
    }

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityMainBinding.inflate(inflater)

    fun initialize() {
        activityUtils.setFragment(HomeFragment(context, activityUtils))
    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun initBottomNav() {
        binding.bottomNav.onClickHelper(this)
    }

    override fun setFragment(type: FragmentType) {

        val fragment = when (type) {
            FragmentType.HOME -> HomeFragment(context, activityUtils)
            FragmentType.CAKE -> CakeCatsFragment(context)
            FragmentType.PASTRY -> PastryCatsFragment(context)
            FragmentType.PROFILE -> ProfileFragment(context)
        }

        activityUtils.setFragment(fragment)

    }

}