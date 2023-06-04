package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.adapter.recycler.CatsRecyclerAdapter
import info.alirezaahmadi.frenchpastry.androidWrapper.PicassoHandler
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ParentCategoryModel
import info.alirezaahmadi.frenchpastry.databinding.FragmentPastryCatsBinding

class ViewPastryCatsFragment(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        FragmentPastryCatsBinding.inflate(LayoutInflater.from(context))

    fun setDataRecycler(cats: ParentCategoryModel) {

        binding.recyclerPastryCats.layoutManager =
            GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)

        binding.recyclerPastryCats.adapter = CatsRecyclerAdapter(cats.categories, context)

        if (cats.banner.isNotEmpty())
            PicassoHandler.setPicassoBanner(binding.imgCatsBanner, cats.banner)

    }

    fun startGetData() {
        binding.cardView.visibility = View.INVISIBLE
        binding.recyclerPastryCats.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.cardView.visibility = View.VISIBLE
        binding.recyclerPastryCats.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

}