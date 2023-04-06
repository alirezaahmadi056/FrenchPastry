package info.alirezaahmadi.frenchpastry.mvp.view

import android.content.Context
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import info.alirezaahmadi.frenchpastry.adapter.recycler.PastryCatsRecyclerAdapter
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.pastryCat.CategoriesModel
import info.alirezaahmadi.frenchpastry.databinding.FragmentPastryCatsBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils

class ViewPastryCatsFragment(
    contextInstance: Context
) : FrameLayout(contextInstance) {

    val binding =
        FragmentPastryCatsBinding.inflate(LayoutInflater.from(context))

    fun setDataRecycler(cats: ArrayList<CategoriesModel>) {

        binding.recyclerPastryCats.layoutManager =
            GridLayoutManager(context, 3, RecyclerView.VERTICAL, false)

        binding.recyclerPastryCats.adapter = PastryCatsRecyclerAdapter(cats)

    }

    fun toast(text: String, errorState: Boolean) {

        if (errorState)
            ToastUtils.toastServerError(context)
        else
            ToastUtils.toast(context, text)

    }

}