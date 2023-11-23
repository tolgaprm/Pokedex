package com.prmto.poxedex.uiComponents.featureStatsWidget.binding

import androidx.databinding.BindingAdapter
import com.prmto.poxedex.uiComponents.featureStatsWidget.FeatureStatsWidget

@BindingAdapter("hpValue")
fun setHpTitle(
    view: FeatureStatsWidget,
    value: Int
) {
    view.setHpValue(value)
}

@BindingAdapter("attackValue")
fun setAttackTitle(
    view: FeatureStatsWidget,
    value: Int
) {
    view.setAttackValue(value)
}

@BindingAdapter("defenseValue")
fun setDefenseTitle(
    view: FeatureStatsWidget,
    value: Int
) {
    view.setDefenseValue(value)
}

@BindingAdapter("specialAttackValue")
fun setSpecialAttackTitle(
    view: FeatureStatsWidget,
    value: Int
) {
    view.setSpecialAttackValue(value)
}

@BindingAdapter("specialDefenseValue")
fun setSpecialDefenseTitle(
    view: FeatureStatsWidget,
    value: Int
) {
    view.setSpecialDefenseValue(value)
}

@BindingAdapter("speedValue")
fun setSpeedTitle(
    view: FeatureStatsWidget,
    value: Int
) {
    view.setSpeedValue(value)
}