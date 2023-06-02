package info.alirezaahmadi.frenchpastry.ui.customView

import android.content.Context
import android.text.Editable
import android.text.InputFilter
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import info.alirezaahmadi.frenchpastry.R
import info.alirezaahmadi.frenchpastry.databinding.CustomEditTextBinding

class CustomEditText(
    context: Context,
    attributeSet: AttributeSet
) : FrameLayout(context, attributeSet) {

    private val binding =
        CustomEditTextBinding.inflate(LayoutInflater.from(context))

    /*
    TYPE_CLASS_TEXT = 1
    TYPE_CLASS_NUMBER = 2
    TYPE_CLASS_PHONE = 3
    */

    init {

        addView(binding.root)

        initialize(attributeSet)

    }

    private fun initialize(attrs: AttributeSet) {

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomEditText)

        val hint = typeArray.getString(R.styleable.CustomEditText_hintText)
        val type = typeArray.getInteger(R.styleable.CustomEditText_type, 1)
        val rtlSupport =
            typeArray.getBoolean(R.styleable.CustomEditText_rtlSupport, false)
        val maxLength = typeArray.getInteger(R.styleable.CustomEditText_max, 0)
        val centerGravity =
            typeArray.getBoolean(R.styleable.CustomEditText_centerGravity, false)

        binding.textInputEditText.hint = hint
        binding.textInputEditText.inputType = type

        if (rtlSupport) {
            binding.textInputEditText.textDirection = TEXT_DIRECTION_RTL
            binding.textInputLayout.layoutDirection = LAYOUT_DIRECTION_RTL
        }

        if (maxLength > 0)
            binding.textInputEditText.filters = arrayOf(InputFilter.LengthFilter(maxLength))

        if (centerGravity)
            binding.textInputEditText.gravity = Gravity.CENTER

        typeArray.recycle()

    }

    fun setError(errorText: String?) {
        binding.textInputLayout.error = errorText
    }

    fun nullError() {
        binding.textInputLayout.error = null
    }

    fun getText() = binding.textInputEditText.text.toString()

    fun setText(text: String) {
        binding.textInputEditText.text = Editable.Factory().newEditable(text)
    }

    fun getEditText() = binding.textInputEditText

}