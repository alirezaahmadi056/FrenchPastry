package info.alirezaahmadi.frenchpastry.ui.customView.bottomNav

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.databinding.CustomBottomNavigationBinding

class CustomBottomNavigation(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {

    private val binding: CustomBottomNavigationBinding

    init {

        binding = CustomBottomNavigationBinding.inflate(LayoutInflater.from(context))
        addView(binding.root)
        binding.txtShoppingCount.text = "0"

    }

    fun onClickHelper(activeFragment: ActiveFragment) {

        binding.homes.setOnClickListener {
            activeHome()
            activeFragment.setFragment(FragmentType.HOME)
        }

        binding.cake.setOnClickListener {
            activeCake()
            activeFragment.setFragment(FragmentType.CAKE)
        }

        binding.pastry.setOnClickListener {
            activePastry()
            activeFragment.setFragment(FragmentType.PASTRY)
        }

        binding.profile.setOnClickListener {
            activeProfile()
            activeFragment.setFragment(FragmentType.PROFILE)
        }

    }

    private fun activeHome() {

        binding.homes.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.cake.background = null
        binding.pastry.background = null
        binding.profile.background = null

    }

    private fun activeCake() {

        binding.cake.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.homes.background = null
        binding.pastry.background = null
        binding.profile.background = null

    }

    private fun activePastry() {

        binding.pastry.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.homes.background = null
        binding.cake.background = null
        binding.profile.background = null

    }

    private fun activeProfile() {

        binding.profile.setBackgroundResource(R.drawable.back_item_bottom_nav)
        binding.homes.background = null
        binding.cake.background = null
        binding.pastry.background = null

    }

    fun changeCount(count: String) {
        binding.off.visibility = VISIBLE
        binding.txtShoppingCount.text = count
    }

}