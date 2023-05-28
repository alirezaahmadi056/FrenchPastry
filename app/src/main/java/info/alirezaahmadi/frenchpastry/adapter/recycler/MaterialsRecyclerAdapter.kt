package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Materials
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemListPastryMaterialsBinding

class MaterialsRecyclerAdapter(
    private val materials: ArrayList<Materials>
) : RecyclerView.Adapter<MaterialsRecyclerAdapter.MaterialsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = MaterialsViewHolder(
        RecyclerItemListPastryMaterialsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = materials.size

    override fun onBindViewHolder(holder: MaterialsViewHolder, position: Int) {
        holder.setData(materials[position])
    }

    inner class MaterialsViewHolder(
        private val binding: RecyclerItemListPastryMaterialsBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: Materials) {

            binding.txtMaterial.text = data.material
            binding.txtAmount.text = data.amount

            if (adapterPosition % 2 != 0)
                binding.root.setBackgroundResource(R.color.white_color200)

        }

    }

}