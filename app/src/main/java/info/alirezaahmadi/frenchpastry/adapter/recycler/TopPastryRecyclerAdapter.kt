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

class TopPastryRecyclerAdapter(
    private val pastries: ArrayList<PastriesModel>
) : RecyclerView.Adapter<TopPastryRecyclerAdapter.SpecialOfferPastryViewHolder>() {

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

            binding.root.visibility = View.VISIBLE
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