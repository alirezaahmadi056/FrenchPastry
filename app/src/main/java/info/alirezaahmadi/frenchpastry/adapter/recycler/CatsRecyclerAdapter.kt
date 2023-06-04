package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.androidWrapper.PicassoHandler
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.CategoriesModel
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemMainCategoriesBinding
import info.alirezaahmadi.frenchpastry.ui.activity.ListPastryActivity

class CatsRecyclerAdapter(
    private val cats: ArrayList<CategoriesModel>,
    private val context: Context
) : RecyclerView.Adapter<CatsRecyclerAdapter.PastryCatsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PastryCatsViewHolder(
        RecyclerItemMainCategoriesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = cats.size

    override fun onBindViewHolder(holder: PastryCatsViewHolder, position: Int) {
        holder.setData(cats[position])
    }

    inner class PastryCatsViewHolder(
        private val binding: RecyclerItemMainCategoriesBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: CategoriesModel) {

            binding.txtCategory.text = data.title

            if (data.thumbnail.isNotEmpty())
                PicassoHandler.setPicassoCats(binding.imgCategory, data.thumbnail)

            binding.root.setOnClickListener {
                val intent = Intent(context, ListPastryActivity::class.java)
                intent.putExtra(ListPastryActivity.ID, data.ID)
                context.startActivity(intent)
            }

        }

    }

}