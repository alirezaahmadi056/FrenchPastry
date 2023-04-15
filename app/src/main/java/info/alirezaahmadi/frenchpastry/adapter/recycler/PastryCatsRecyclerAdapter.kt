package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.pastryCat.CategoriesModel
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemMainPastryCategoriesBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.ToastUtils
import info.alirezaahmadi.frenchpastry.ui.activity.ListCategoryActivity

class PastryCatsRecyclerAdapter(
    private val cats: ArrayList<CategoriesModel>,
    private val context: Context
) : RecyclerView.Adapter<PastryCatsRecyclerAdapter.PastryCatsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PastryCatsViewHolder(
        RecyclerItemMainPastryCategoriesBinding.inflate(
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
        private val binding: RecyclerItemMainPastryCategoriesBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: CategoriesModel) {

            binding.txtCategory.text = data.title

            if (data.thumbnail.isNotEmpty())
                Picasso.get()
                    .load(data.thumbnail)
                    .fit()
                    .placeholder(R.drawable.ic_pastry_place_holder)
                    .error(R.drawable.ic_pastry_place_holder)
                    .into(binding.imgCategory)

            binding.root.setOnClickListener {
                context.startActivity(Intent(context, ListCategoryActivity::class.java))
            }

        }

    }

}