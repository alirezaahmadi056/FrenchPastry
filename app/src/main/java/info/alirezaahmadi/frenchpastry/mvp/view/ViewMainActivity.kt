package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.Toast
import info.alirezaahmadi.frenchpastry.databinding.ActivityMainBinding
import info.alirezaahmadi.frenchpastry.ui.customView.bottomNav.ActiveFragment
import info.alirezaahmadi.frenchpastry.ui.customView.bottomNav.FragmentType

class ViewMainActivity(
    contextInstance: Context
) : FrameLayout(contextInstance), ActiveFragment {

    private val inflater = LayoutInflater.from(context)
    val binding = ActivityMainBinding.inflate(inflater)

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun initBottomNav() {
        binding.bottomNav.onClickHelper(this)
    }

    override fun setFragment(type: FragmentType) {
        Toast.makeText(context, type.toString(), Toast.LENGTH_SHORT).show()
    }

}