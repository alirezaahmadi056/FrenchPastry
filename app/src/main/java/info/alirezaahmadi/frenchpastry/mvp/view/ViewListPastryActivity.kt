package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.alirezaahmadi.frenchpastry.adapter.recycler.PastryListRecyclerAdapter
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryListModel
import info.alirezaahmadi.frenchpastry.databinding.ActivityListPastryBinding

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

    fun onBack() {
        actUtils.finished()
    }

    fun setData(pastries: ArrayList<PastryListModel>) {

        binding.recyclerViewPastry.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        binding.recyclerViewPastry.adapter = PastryListRecyclerAdapter(pastries)

    }

}