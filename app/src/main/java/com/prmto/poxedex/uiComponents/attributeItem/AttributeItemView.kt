package com.prmto.poxedex.uiComponents.attributeItem

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.prmto.poxedex.R
import com.prmto.poxedex.databinding.AttributeItemViewBinding

class AttributeItemView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: AttributeItemViewBinding =
        AttributeItemViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        binding.llAttributeItemView.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.AttributeItemView)

        styledAttributes.apply {
            val attributeIcon = getResourceId(R.styleable.AttributeItemView_attributeIcon, 0)
            val attributeIconRotate =
                getFloat(R.styleable.AttributeItemView_attributeIconRotate, 0.0f)
            val attributeTitle = getString(R.styleable.AttributeItemView_attributeTitle) ?: ""
            val attributeValue = getString(R.styleable.AttributeItemView_attributeValue) ?: ""
            val attributeMoves = getString(R.styleable.AttributeItemView_attributeMoves) ?: ""

            if (attributeIcon == 0) {
                binding.imvAttributeIcon.isVisible = false
            }

            binding.imvAttributeIcon.setImageResource(attributeIcon)
            binding.imvAttributeIcon.rotation = attributeIconRotate
            binding.tvAttributeTitle.text = attributeTitle
            setAttributeValue(attributeValue)
            setAttributeMoves(attributeMoves)
        }
        styledAttributes.recycle()
    }

    // These methods are public because they are used in the binding adapter

    fun setAttributeValue(value: String) {
        if (value.isEmpty()) {
            return
        }
        binding.tvAttributeValue.text = value
    }

    fun setAttributeMoves(values: String) {
        binding.tvAttributeValue.text = values.split(",").joinToString("\n")
    }
}