package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.alirezaahmadi.frenchpastry.adapter.recycler.ProductListRecyclerAdapter
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.AllPastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.ListPastriesModel
import info.alirezaahmadi.frenchpastry.databinding.ActivityListPastryBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

class ViewListPastryActivity : FrameLayout {

    private lateinit var actUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        activityUtils: ActivityUtils
    ) : super(contextInstance) {
        actUtils = activityUtils
    }

    val binding = ActivityListPastryBinding.inflate(
        LayoutInflater.from(context)
    )

    fun showNavDrawer() {
        binding.customAppBar.showNavDrawer(context)
    }

    fun onBack() {
        binding.customAppBar.getBackIcon().setOnClickListener {
            actUtils.finished()
        }
    }

    fun setData(data: ListPastriesModel) {

        binding.recyclerViewPastry.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val adapter = ProductListRecyclerAdapter(data.category.pastries, context)
        binding.recyclerViewPastry.adapter = adapter

        binding.txtTitle.text = data.category.title

        binding.edtSearch.getEditText().doOnTextChanged { text, _, _, _ ->
            adapter.filter.filter(text)
        }

    }

    fun setData2(data: AllPastriesModel, title: String) {

        binding.recyclerViewPastry.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        val adapter = ProductListRecyclerAdapter(data.pastries, context)
        binding.recyclerViewPastry.adapter = adapter

        binding.edtSearch.getEditText().doOnTextChanged { text, _, _, _ ->
            adapter.filter.filter(text)
        }

        binding.txtTitle.text = title

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