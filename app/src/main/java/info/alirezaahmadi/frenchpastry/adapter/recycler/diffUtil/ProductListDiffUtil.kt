package info.alirezaahmadi.frenchpastry.adapter.recycler.diffUtil

import androidx.recyclerview.widget.DiffUtil
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.PastryModel

class ProductListDiffUtil(
    private val oldList: ArrayList<PastryModel>,
    private val newList: ArrayList<PastryModel>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].ID == newList[newItemPosition].ID

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList == newList

}