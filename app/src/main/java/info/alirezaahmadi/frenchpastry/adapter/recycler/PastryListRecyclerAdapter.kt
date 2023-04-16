package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryListModel
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemListCategoryBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.OthersUtilities

class PastryListRecyclerAdapter(
    private val pastries: ArrayList<PastryListModel>
) : RecyclerView.Adapter<PastryListRecyclerAdapter.PastryListViewHolder>() {

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) :PastryListViewHolder{

        context = parent.context

        return PastryListViewHolder(
            RecyclerItemListCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount() = pastries.size

    override fun onBindViewHolder(holder: PastryListViewHolder, position: Int) {
        holder.setData(pastries[position])
    }

    inner class PastryListViewHolder(
        private val binding: RecyclerItemListCategoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: PastryListModel) {

            binding.txtTitle.text = data.title
            binding.txtPriceMain.text = OthersUtilities.changePrice(data.price).toString()

            if (data.thumbnail.isNotEmpty())
                Picasso.get()
                    .load(data.thumbnail)
                    .fit()
                    .placeholder(R.drawable.img_place_holder)
                    .error(R.drawable.img_place_holder)
                    .into(binding.imgPastry)

            if (data.has_discount){

                binding.txtPriceMain.paintFlags =
                    binding.txtPriceMain.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtPriceMain.setTextColor(Color.GRAY)

                binding.txtPriceOff.text = OthersUtilities.changePrice(data.sale_price).toString()
                binding.txtOff.text = data.discount

            }else
                binding.off.visibility = View.GONE

        }

    }

}