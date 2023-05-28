package info.alirezaahmadi.frenchpastry.adapter.recycler.diffUtil

import androidx.recyclerview.widget.DiffUtil

class RecyclerDiffUtil(
    private val oldList: ArrayList<*>,
    private val newList: ArrayList<*>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList == newList

}