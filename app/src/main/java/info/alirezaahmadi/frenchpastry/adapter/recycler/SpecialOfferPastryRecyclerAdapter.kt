package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastriesModel
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemMainVerticalBinding
import info.alirezaahmadi.frenchpastry.mvp.ext.OthersUtilities
import info.alirezaahmadi.frenchpastry.ui.activity.ListPastryActivity

class SpecialOfferPastryRecyclerAdapter(
    private val pastries: ArrayList<PastriesModel>,
    private val context: Context
) : RecyclerView.Adapter<SpecialOfferPastryRecyclerAdapter.SpecialOfferPastryViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SpecialOfferPastryViewHolder(
        RecyclerItemMainVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = pastries.size

    override fun onBindViewHolder(holder: SpecialOfferPastryViewHolder, position: Int) {
        holder.setData(pastries[position], position)
    }

    inner class SpecialOfferPastryViewHolder(
        private val binding: RecyclerItemMainVerticalBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: PastriesModel, position: Int) {

            if (position == 0) {

                binding.root.visibility = View.INVISIBLE

            } else {

                binding.root.visibility = View.VISIBLE
                binding.txtPastryName.text = data.title
                binding.txtMainPrice.text = OthersUtilities.changePrice(data.price).toString()

                if (data.has_discount) {

                    binding.txtMainPrice.paintFlags =
                        binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    binding.txtMainPrice.setTextColor(Color.GRAY)

                    binding.txtOffPrice.text =
                        OthersUtilities.changePrice(data.sale_price).toString()
                    binding.txtOff.text = data.discount

                } else
                    binding.off.visibility = View.GONE

                if (data.thumbnail.isNotEmpty())
                    Picasso.get()
                        .load(data.thumbnail)
                        .fit()
                        .placeholder(R.drawable.img_place_holder)
                        .error(R.drawable.img_place_holder)
                        .into(binding.imgPastry)

                if (position == pastries.lastIndex) {

                    binding.allViews.visibility = View.INVISIBLE
                    binding.others.visibility = View.VISIBLE

                    binding.root.setOnClickListener {
                        val intent = Intent(context, ListPastryActivity::class.java)
                        intent.putExtra(ListPastryActivity.TYPE, ListPastryActivity.SPECIAL_OFFER)
                        context.startActivity(intent)
                    }

                }

            }

        }

    }

}