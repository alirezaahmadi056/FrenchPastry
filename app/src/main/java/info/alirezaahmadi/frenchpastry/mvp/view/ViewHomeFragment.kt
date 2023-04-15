package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
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
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.RequestMain
import info.alirezaahmadi.frenchpastry.databinding.FragmentHomeBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

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

    fun initialized(data: RequestMain) {

        activityUtils.setViewPagerFragment(binding.sliderViewPager, data.sliders)

        //todo change to recycler.addAll(pastry)
        binding.newPastryRecycler.getRecycler().adapter =
            NewPastryRecyclerAdapter(data.pastries[0].pastries)

        binding.specialOfferPastryRecycler.getRecycler().adapter =
            SpecialOfferPastryRecyclerAdapter(data.pastries[1].pastries)

        binding.topPastryRecycler.getRecycler().adapter =
            TopPastryRecyclerAdapter(data.pastries[2].pastries)

        if (data.banners.isNotEmpty() && data.banners[0].large.isNotEmpty())
            Picasso.get()
                .load(data.banners[0].large)
                .fit()
                .placeholder(R.drawable.img_banner_place_holder)
                .error(R.drawable.img_banner_place_holder)
                .into(binding.imgBanner)

    }

    fun setFakeData(data: RequestMain) {

        binding.sliderViewPager.layoutDirection = View.LAYOUT_DIRECTION_RTL
        activityUtils.setViewPagerFragment(binding.sliderViewPager, data.sliders)

        binding.newPastryRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        binding.newPastryRecycler.getRecycler().adapter =
            NewPastryRecyclerAdapter(data.pastries[0].pastries)

        binding.specialOfferPastryRecycler.getRecycler().layoutManager =
            LinearLayoutManager(context, RecyclerView.HORIZONTAL, true)
        binding.specialOfferPastryRecycler.getRecycler().adapter =
            SpecialOfferPastryRecyclerAdapter(data.pastries[1].pastries)

        binding.topPastryRecycler.getRecycler().layoutManager =
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)
        binding.topPastryRecycler.getRecycler().adapter =
            TopPastryRecyclerAdapter(data.pastries[2].pastries)

        Picasso.get()
            .load(data.banners[0].large)
            .fit()
            .placeholder(R.drawable.img_place_holder)
            .error(R.drawable.img_place_holder)
            .into(binding.imgBanner)

    }

    fun toast(text: String, errorState: Boolean) {

        if (errorState)
            ToastUtils.toastServerError(context)
        else
            ToastUtils.toast(context, text)

    }

}