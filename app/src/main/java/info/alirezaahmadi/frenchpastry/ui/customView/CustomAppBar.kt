package info.alirezaahmadi.frenchpastry.ui.customView

import android.content.Context
import android.content.Intent
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.databinding.CustomAppBarBinding
import info.alirezaahmadi.frenchpastry.ui.activity.NavDrawerActivity

class CustomAppBar(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding =
        CustomAppBarBinding.inflate(LayoutInflater.from(context))

    init {

        addView(binding.root)

    }

    fun alertShow() {
        binding.alertGroup.visibility = VISIBLE
    }

    fun alertHide() {
        binding.alertGroup.visibility = INVISIBLE
    }

    fun setAlertCount(count: String) {
        binding.txtAlertCount.text = count
    }

    fun showNavDrawer(context: Context) {

        binding.imgMenu.setOnClickListener {
            context.startActivity(Intent(context, NavDrawerActivity::class.java))
        }

    }

}