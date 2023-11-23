package com.prmto.poxedex.uiComponents.featureStatsWidget

import android.animation.ObjectAnimator
import android.content.Context
import android.content.res.ColorStateList
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import com.prmto.poxedex.R
import com.prmto.poxedex.databinding.FeatureStatViewBinding

class FeatureStatsWidget @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private val binding: FeatureStatViewBinding =
        FeatureStatViewBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
        binding.llStateView.layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT,
        )
        val styledAttributes = context.obtainStyledAttributes(attrs, R.styleable.FeatureStatsWidget)

        styledAttributes.apply {
            setAllTitles()
            setAllValues()
            setProgressTint(
                R.styleable.FeatureStatsWidget_progressTint,
                binding.stateHp.progressBar
            )
        }

        styledAttributes.recycle()
    }

    private fun TypedArray.setProgressTint(
        styleable: Int,
        progressBar: ContentLoadingProgressBar
    ) {
        val color = getResourceId(styleable, 0)
        if (color == 0) return
        progressBar.progressTintList = context.getColorStateList(color)
    }

    private fun TypedArray.setItemTitle(styleable: Int, textView: TextView) {
        val title = getString(styleable) ?: ""
        textView.text = title
    }

    private fun TypedArray.setAllTitles() {
        setItemTitle(R.styleable.FeatureStatsWidget_hpTitle, binding.stateHp.tvStateItemTitle)
        setItemTitle(
            R.styleable.FeatureStatsWidget_attackTitle,
            binding.stateAttack.tvStateItemTitle
        )
        setItemTitle(
            R.styleable.FeatureStatsWidget_defenseTitle,
            binding.stateDefense.tvStateItemTitle
        )
        setItemTitle(
            R.styleable.FeatureStatsWidget_specialAttackTitle,
            binding.stateSpecialAttack.tvStateItemTitle
        )
        setItemTitle(
            R.styleable.FeatureStatsWidget_specialDefenseTitle,
            binding.stateSpecialDefense.tvStateItemTitle
        )
        setItemTitle(R.styleable.FeatureStatsWidget_speedTitle, binding.stateSpeed.tvStateItemTitle)
    }

    private fun setAllValues() {
        setHpValue(R.styleable.FeatureStatsWidget_hpValue)
        setAttackValue(R.styleable.FeatureStatsWidget_attackValue)
        setDefenseValue(R.styleable.FeatureStatsWidget_defenseValue)
        setSpecialAttackValue(R.styleable.FeatureStatsWidget_specialAttackValue)
        setSpecialDefenseValue(R.styleable.FeatureStatsWidget_specialDefenseValue)
        setSpeedValue(R.styleable.FeatureStatsWidget_speedValue)
    }

    fun setAllProgressTints(color: Int) {
        binding.apply {
            stateHp.progressBar.progressTintList = ColorStateList.valueOf(color)
            stateAttack.progressBar.progressTintList = ColorStateList.valueOf(color)
            stateDefense.progressBar.progressTintList = ColorStateList.valueOf(color)
            stateSpecialAttack.progressBar.progressTintList = ColorStateList.valueOf(color)
            stateSpecialDefense.progressBar.progressTintList = ColorStateList.valueOf(color)
            stateSpeed.progressBar.progressTintList = ColorStateList.valueOf(color)
        }
    }

    fun setAllTitleColors(color: Int) {
        binding.apply {
            stateHp.tvStateItemTitle.setTextColor(color)
            stateAttack.tvStateItemTitle.setTextColor(color)
            stateDefense.tvStateItemTitle.setTextColor(color)
            stateSpecialAttack.tvStateItemTitle.setTextColor(color)
            stateSpecialDefense.tvStateItemTitle.setTextColor(color)
            stateSpeed.tvStateItemTitle.setTextColor(color)
        }
    }

    private fun setItemFormattedValue(value: Int, textView: TextView) {
        textView.text = String.format("%03d", value)
    }

    // These methods are public because they are used in the binding adapter
    fun setHpValue(value: Int) {
        setItemFormattedValue(value, binding.stateHp.tvStateItemValue)
        setProgressValueAndStartAnimation(binding.stateHp.progressBar, value)
    }

    fun setAttackValue(value: Int) {
        setItemFormattedValue(value, binding.stateAttack.tvStateItemValue)
        setProgressValueAndStartAnimation(binding.stateAttack.progressBar, value)
    }

    fun setDefenseValue(value: Int) {
        setItemFormattedValue(value, binding.stateDefense.tvStateItemValue)
        setProgressValueAndStartAnimation(binding.stateDefense.progressBar, value)
    }

    fun setSpecialAttackValue(value: Int) {
        setItemFormattedValue(value, binding.stateSpecialAttack.tvStateItemValue)
        setProgressValueAndStartAnimation(binding.stateSpecialAttack.progressBar, value)
    }

    fun setSpecialDefenseValue(value: Int) {
        setItemFormattedValue(value, binding.stateSpecialDefense.tvStateItemValue)
        setProgressValueAndStartAnimation(binding.stateSpecialDefense.progressBar, value)
    }

    fun setSpeedValue(value: Int) {
        setItemFormattedValue(value, binding.stateSpeed.tvStateItemValue)
        setProgressValueAndStartAnimation(binding.stateSpeed.progressBar, value)
    }

    private fun setProgressValueAndStartAnimation(
        progressBar: ContentLoadingProgressBar,
        value: Int
    ) {
        progressBar.progress = value
        val animationDuration = 3000L

        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, value)
        progressAnimator.duration = animationDuration
        progressAnimator.start()
    }
}