package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.databinding.ActivityAddressBinding

class ViewAddressActivity : FrameLayout {

    private lateinit var actUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        activityUtils: ActivityUtils
    ) : super(contextInstance) {
        actUtils = activityUtils
    }

    val binding =
        ActivityAddressBinding.inflate(LayoutInflater.from(context))

    fun setDataRecycler() {

    }

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun onBack() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            actUtils.finished()
        }
    }

    fun startGetData() {
        //binding.allViews.visibility = View.INVISIBLE
        //binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        //binding.allViews.visibility = View.VISIBLE
        //binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        //binding.progressBar.visibility = View.INVISIBLE
    }

}