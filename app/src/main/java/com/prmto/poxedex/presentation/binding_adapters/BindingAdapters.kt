package com.prmto.poxedex.presentation.binding_adapters

import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.prmto.poxedex.R
import com.prmto.poxedex.presentation.pokedex_list.SortType

@BindingAdapter("loadImage")
fun loadImage(view: ImageView, url: String?) {
    if (url != null) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}

@BindingAdapter("formatNumber")
fun formatNumber(view: TextView, number: Int) {
    view.text = String.format("#%03d", number)
}

@BindingAdapter("sortType")
fun sortType(imageButton: ImageButton, sortType: SortType) {
    val iconResId = when (sortType) {
        SortType.NAME -> R.drawable.text_format
        SortType.NUMBER -> R.drawable.tag
    }
    imageButton.setImageResource(iconResId)
}