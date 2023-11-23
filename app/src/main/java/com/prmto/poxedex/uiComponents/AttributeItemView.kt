package com.prmto.poxedex.uiComponents

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.prmto.poxedex.R
import com.prmto.poxedex.databinding.AttributeItemViewBinding

class AttributeItemView @JvmOverloads constructor(
    private val context: Context,
    private val attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: AttributeItemViewBinding =
        AttributeItemViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        getStuffFromXml()
    }

    private fun getStuffFromXml() {
        val data = context.obtainStyledAttributes(attrs, R.styleable.AttributeItemView)

        data.apply {
            val attributeIcon = data.getResourceId(R.styleable.AttributeItemView_attributeIcon, 0)
            val attributeIconRotate =
                data.getFloat(R.styleable.AttributeItemView_attributeIconRotate, 0.0f)
            val attributeTitle = data.getString(R.styleable.AttributeItemView_attributeTitle) ?: ""
            val attributeValue = data.getString(R.styleable.AttributeItemView_attributeValue) ?: ""
            val attributeValues =
                data.getString(R.styleable.AttributeItemView_attributeValues) ?: ""

            if (attributeIcon == 0) {
                binding.imvAttributeIcon.isVisible = false
            }

            binding.imvAttributeIcon.setImageResource(attributeIcon)
            binding.imvAttributeIcon.rotation = attributeIconRotate
            binding.tvAttributeTitle.text = attributeTitle
            if (attributeValue.isNotEmpty()) {
                binding.tvAttributeValue.text = attributeValue
            }

            if (attributeValues.isNotEmpty()) {
                binding.tvAttributeValue.text = attributeValues.split(" ").joinToString("\n")
            }
            recycle()
        }
    }
}