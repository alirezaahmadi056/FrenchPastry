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
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemMainHorizontalBinding

class NewPastryRecyclerAdapter(
    private val pastries: ArrayList<PastriesModel>
) : RecyclerView.Adapter<NewPastryRecyclerAdapter.NewPastryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewPastryViewHolder {
        return NewPastryViewHolder(
            RecyclerItemMainHorizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = pastries.size

    override fun onBindViewHolder(holder: NewPastryViewHolder, position: Int) {
        holder.setData(pastries[position])
    }

    inner class NewPastryViewHolder(
        private val binding: RecyclerItemMainHorizontalBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: PastriesModel) {

            binding.txtPastryName.text = data.title
            binding.txtMainPrice.text = data.price.toString()

            if (data.sale_price != 0) {

                binding.txtMainPrice.paintFlags =
                    binding.txtMainPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
                binding.txtMainPrice.setTextColor(Color.GRAY)

                binding.txtOffPrice.text = data.price.toString()
                binding.txtOff.text = "50%"

            } else {

                binding.txtOff.visibility = View.GONE
                binding.txtOffPrice.visibility = View.GONE
                binding.imgOff.visibility = View.GONE

            }

            Picasso.get()
                .load(data.thumbnail)
                .fit()
                .placeholder(R.drawable.img_place_holder)
                .error(R.drawable.img_place_holder)
                .into(binding.imgPastry)

        }
    }

}