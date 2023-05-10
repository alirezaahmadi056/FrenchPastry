package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.adapter.recycler.NewPastryRecyclerAdapter
import info.alirezaahmadi.frenchpastry.adapter.recycler.SpecialOfferPastryRecyclerAdapter
import info.alirezaahmadi.frenchpastry.adapter.recycler.TopPastryRecyclerAdapter
import info.alirezaahmadi.frenchpastry.androidWrapper.ActivityUtils
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastriesModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.RequestMain
import info.alirezaahmadi.frenchpastry.databinding.FragmentHomeBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.ui.activity.ListPastryActivity

class ViewHomeFragment : FrameLayout {

    private lateinit var activityUtils: ActivityUtils

    constructor(contextInstance: Context) : super(contextInstance)

    constructor(
        contextInstance: Context,
        actUtils: ActivityUtils
    ) : super(contextInstance) {
        activityUtils = actUtils
    }

    val binding =
        FragmentHomeBinding.inflate(LayoutInflater.from(context))

    fun startGetData() {
        binding.content.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE
    }

    fun endGetData() {
        binding.content.visibility = View.VISIBLE
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun endProgress() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    fun initialized(data: RequestMain) {

        binding.sliderViewPager.layoutDirection = View.LAYOUT_DIRECTION_RTL
        activityUtils.setViewPagerFragment(binding.sliderViewPager, data.sliders)

        binding.newPastryRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        binding.newPastryRecycler.getRecycler().adapter =
            NewPastryRecyclerAdapter(data.pastries[0].pastries)

        binding.specialOfferPastryRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        val specialOfferData = data.pastries[1].pastries
        specialOfferData.add(
            0,
            PastriesModel(
                "",
                0,
                "",
                0,
                0,
                false,
                ""
            )
        )
        specialOfferData.add(
            PastriesModel(
                "",
                0,
                "",
                0,
                0,
                false,
                ""
            )
        )
        binding.specialOfferPastryRecycler.getRecycler().adapter =
            SpecialOfferPastryRecyclerAdapter(specialOfferData, context)

        binding.topPastryRecycler.getRecycler().layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        binding.topPastryRecycler.getRecycler().adapter =
            TopPastryRecyclerAdapter(data.pastries[2].pastries)

        if (data.banners.isNotEmpty() && data.banners[0].large.isNotEmpty())
            Picasso.get()
                .load(data.banners[0].large)
                .fit()
                .placeholder(R.drawable.img_banner_place_holder)
                .error(R.drawable.img_banner_place_holder)
                .into(binding.imgBanner)

        binding.newPastryRecycler.getAll().setOnClickListener {
            val intent = Intent(context, ListPastryActivity::class.java)
            intent.putExtra(ListPastryActivity.TYPE, ListPastryActivity.NEW)
            context.startActivity(intent)
        }

        binding.topPastryRecycler.getAll().setOnClickListener {
            val intent = Intent(context, ListPastryActivity::class.java)
            intent.putExtra(ListPastryActivity.TYPE, ListPastryActivity.RATE)
            context.startActivity(intent)
        }

    }

    fun toast() {
        ToastUtils.toastServerError(context)
    }

}