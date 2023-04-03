package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.graphics.Color
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.main.PastriesModel
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemMainVerticalBinding

class SpecialOfferPastryRecyclerAdapter(
    private val pastries: ArrayList<PastriesModel>
) : RecyclerView.Adapter<SpecialOfferPastryRecyclerAdapter.SpecialOfferPastryViewHolder>() {

    private val headerItem = 0
    private val otherItem = 1

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SpecialOfferPastryViewHolder {
        return if (viewType == headerItem)
            SpecialOfferPastryViewHolder(
                RecyclerItemMainVerticalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                true
            )
        else
            SpecialOfferPastryViewHolder(
                RecyclerItemMainVerticalBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                ),
                false
            )
    }

    override fun getItemCount() = pastries.size

    override fun onBindViewHolder(holder: SpecialOfferPastryViewHolder, position: Int) {
        holder.setData(pastries[position])
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) headerItem else otherItem
    }

    inner class SpecialOfferPastryViewHolder(
        private val binding: RecyclerItemMainVerticalBinding,
        private val header: Boolean
    ) : ViewHolder(binding.root) {

        fun setData(data: PastriesModel) {

            if (header) {

                binding.txtMainPrice.visibility = View.INVISIBLE
                binding.off.visibility = View.INVISIBLE
                binding.txtPastryName.visibility = View.INVISIBLE
                binding.frameLayouts.visibility = View.INVISIBLE
                binding.textView8.visibility = View.INVISIBLE
                binding.imgPastry.visibility = View.INVISIBLE

            } else {

                binding.txtPastryName.text = data.title
                binding.txtMainPrice.text = data.price.toString()

                if (data.has_discount) {

                    binding.txtMainPrice.paintFlags =
                        binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                    binding.txtMainPrice.setTextColor(Color.GRAY)

                    binding.txtOffPrice.text = data.price.toString()
                    binding.txtOff.text = data.discount

                } else
                    binding.off.visibility = View.GONE

                Picasso.get()
                    .load(data.thumbnail)
                    .fit()
                    .placeholder(R.drawable.img_place_holder)
                    .error(R.drawable.img_place_holder)
                    .into(binding.imgPastry)

            }

        }

    }

}