package info.alirezaahmadi.frenchpastry.adapter.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import info.alirezaahmadi.frenchpastry.data.remote.dataModel.Comments
import info.alirezaahmadi.frenchpastry.databinding.RecyclerItemCommentsBinding

class CommentsRecyclerAdapter(
    private val comments: ArrayList<Comments>
) : RecyclerView.Adapter<CommentsRecyclerAdapter.CommentsViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = CommentsViewHolder(
        RecyclerItemCommentsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = comments.size

    override fun onBindViewHolder(holder: CommentsViewHolder, position: Int) {
        holder.setData(comments[position])
    }

    inner class CommentsViewHolder(
        private val binding: RecyclerItemCommentsBinding
    ) : ViewHolder(binding.root) {

        fun setData(data: Comments) {

            binding.txtName.text = data.name
            binding.txtRate.text = data.rate.toString()
            binding.txtContent.text = data.body

        }

    }

}