package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryDetailModel
import info.alirezaahmadi.frenchpastry.databinding.ActivityDetailPastryBinding

class ViewDetailPastryActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        ActivityDetailPastryBinding.inflate(LayoutInflater.from(context))

    fun setData(detail: PastryDetailModel) {

    }

    fun startGetData() {
        binding.allViews.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.allViews.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}