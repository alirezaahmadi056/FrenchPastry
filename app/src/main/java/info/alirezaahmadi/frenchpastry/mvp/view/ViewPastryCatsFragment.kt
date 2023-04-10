package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.adapter.recycler.PastryCatsRecyclerAdapter
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.pastryCat.CategoriesModel
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.pastryCat.PastryCategoryModel
import info.alirezaahmadi.frenchpastry.databinding.FragmentPastryCatsBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

class ViewPastryCatsFragment(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        FragmentPastryCatsBinding.inflate(LayoutInflater.from(context))

    fun setDataRecycler(cats: PastryCategoryModel) {

        //todo change to recycler.addAll(cats)
        binding.recyclerPastryCats.adapter = PastryCatsRecyclerAdapter(cats.categories)

        if (cats.banner.isNotEmpty())
            Picasso.get()
                .load(cats.banner)
                .fit()
                .placeholder(R.drawable.img_banner_place_holder)
                .error(R.drawable.img_banner_place_holder)
                .into(binding.imgCatsBanner)

    }

    fun setFakeData(cats: PastryCategoryModel) {

        binding.recyclerPastryCats.layoutManager =
            GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)

        binding.recyclerPastryCats.adapter = PastryCatsRecyclerAdapter(cats.categories)

        Picasso.get()
            .load(cats.banner)
            .fit()
            .placeholder(R.drawable.img_banner_place_holder)
            .error(R.drawable.img_banner_place_holder)
            .into(binding.imgCatsBanner)

    }

    fun toast(text: String, errorState: Boolean) {

        if (errorState)
            ToastUtils.toastServerError(context)
        else
            ToastUtils.toast(context, text)

    }

}