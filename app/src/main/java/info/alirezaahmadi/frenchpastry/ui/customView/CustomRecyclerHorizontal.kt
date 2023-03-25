package info.alirezaahmadi.frenchpastry.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.databinding.CustomMainRecyclerHorizontalBinding

class CustomRecyclerHorizontal(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val binding: CustomMainRecyclerHorizontalBinding

    init {

        binding = CustomMainRecyclerHorizontalBinding.inflate(
            LayoutInflater.from(context)
        )

        addView(binding.root)

    }

    fun getRecycler() = binding.recyclerView

}