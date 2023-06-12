package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import info.alirezaahmadi.frenchpastry.adapter.recycler.diffUtil.RecyclerDiffUtil
import info.alirezaahmadi.frenchpastry.androidWrapper.PicassoHandler
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryModel
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemListProductsBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.OthersUtilities
import info.alirezaahmadi.frenchpastry.ui.activity.DetailPastryActivity

class ProductListRecyclerAdapter(
    private val pastries: ArrayList<PastryModel>,
    private val context: Context
) : RecyclerView.Adapter<ProductListRecyclerAdapter.PastryListViewHolder>(), Filterable {

    private val dataFull = ArrayList<PastryModel>()
    private val dataMain = ArrayList<PastryModel>()

    init {
        dataFull.addAll(pastries)
        dataMain.addAll(pastries)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = PastryListViewHolder(
        RecyclerItemListProductsBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )
    )

    override fun getItemCount() = pastries.size

    override fun onBindViewHolder(holder: PastryListViewHolder, position: Int) {
        holder.setData(pastries[position])
    }

    inner class PastryListViewHolder(
        private val binding: RecyclerItemListProductsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun setData(data: PastryModel) {

            binding.txtTitle.text = data.title
            binding.txtPriceMain.text = OthersUtilities.changePrice(data.price)

            if (data.thumbnail.isNotEmpty())
                PicassoHandler.setPicasso(binding.imgProduct, data.thumbnail)

            if (data.has_discount) {

                binding.txtPriceMain.paintFlags =
                    binding.txtPriceMain.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtPriceMain.setTextColor(Color.GRAY)

                binding.txtPriceOff.visibility = View.VISIBLE

                binding.txtPriceOff.text = OthersUtilities.changePrice(data.sale_price)
                binding.txtOff.text = data.discount

            } else {
                binding.off.visibility = View.GONE
                binding.txtPriceOff.visibility = View.GONE
            }

            binding.root.setOnClickListener {
                val intent = Intent(context, DetailPastryActivity::class.java)
                intent.putExtra(DetailPastryActivity.ID, data.ID)
                context.startActivity(intent)
            }

        }

    }

    override fun getFilter(): Filter =
        object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {

                val data = ArrayList<PastryModel>()

                if (constraint == null || constraint.isEmpty())
                    data.addAll(dataFull)
                else {
                    val filter = constraint.toString().trim()
                    for (item in dataFull) {
                        if (item.title.contains(filter))
                            data.add(item)
                    }
                }

                pastries.clear()
                pastries.addAll(data)

                return FilterResults()

            }

            override fun publishResults(p0: CharSequence?, result: FilterResults?) {
                dataUpdate(pastries)
            }

        }

    private fun dataUpdate(newList: ArrayList<PastryModel>) {

        val diffCallback = RecyclerDiffUtil(dataMain, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        dataMain.clear()
        dataMain.addAll(newList)

        diffResult.dispatchUpdatesTo(this)

    }

}