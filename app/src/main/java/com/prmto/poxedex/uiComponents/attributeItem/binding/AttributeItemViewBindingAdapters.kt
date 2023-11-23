package com.prmto.poxedex.uiComponents.attributeItem.binding

import androidx.databinding.BindingAdapter
import com.prmto.poxedex.uiComponents.attributeItem.AttributeItemView

@BindingAdapter("attributeValue")
fun setAttributeValue(
    attributeItemView: AttributeItemView,
    attributeValue: String?
) {
    if (attributeValue.isNullOrEmpty()) {
        return
    }
    attributeItemView.setAttributeValue(attributeValue)
}

@BindingAdapter("attributeMoves")
fun setAttributeMoves(
    attributeItemView: AttributeItemView,
    moves: String?
) {
    if (moves.isNullOrEmpty()) {
        return
    }
    attributeItemView.setAttributeMoves(moves)
}