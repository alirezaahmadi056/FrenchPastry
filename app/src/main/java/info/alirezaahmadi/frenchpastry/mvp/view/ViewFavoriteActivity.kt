package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.alirezaahmadi.frenchpastry.adapter.recycler.ProductListRecyclerAdapter
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryModel
import info.alirezaahmadi.frenchpastry.databinding.ActivityFavoriteBinding

class ViewFavoriteActivity(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        ActivityFavoriteBinding.inflate(LayoutInflater.from(context))

    fun setDataRecycler(pastry: ArrayList<PastryModel>) {

        //todo اینجا یه دکمه برای حذف علاقه مندی ها کم داره
        binding.recyclerViewPastry.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        binding.recyclerViewPastry.adapter =
            ProductListRecyclerAdapter(pastry, context)

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