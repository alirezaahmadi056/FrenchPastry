package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.databinding.ActivityFavoriteBinding

class ViewFavoriteActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        ActivityFavoriteBinding.inflate(LayoutInflater.from(context))

    fun setDataRecycler() {

    }

    fun startGetData() {
        /*binding.cardView.visibility = View.INVISIBLE
        binding.recyclerPastryCats.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE*/
    }

    fun endGetData() {
        /*binding.cardView.visibility = View.VISIBLE
        binding.recyclerPastryCats.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE*/
    }

    fun endProgress() {
        //binding.progressBar.visibility = View.INVISIBLE
    }

}