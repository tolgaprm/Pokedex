package com.prmto.poxedex.uiComponents

import android.animation.ObjectAnimator
import android.content.Context
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

        styledAttributes.use {
            with(it) {
                setAllTitles()
                setAllValues()
                setAllProgressValues()
                setProgressTint(
                    R.styleable.FeatureStatsWidget_progressTint,
                    binding.stateHp.progressBar
                )
            }
        }
    }

    private fun TypedArray.setProgressTint(
        styleable: Int,
        progressBar: ContentLoadingProgressBar
    ) {
        val title = getResourceId(styleable, 0)
        progressBar.progressTintList = context.getColorStateList(title)
    }

    private fun TypedArray.setItemTitle(styleable: Int, textView: TextView) {
        val title = getString(styleable) ?: ""
        textView.text = title
    }

    private fun TypedArray.setProgressValue(
        styleable: Int,
        progressBar: ContentLoadingProgressBar
    ) {
        val value = getInt(styleable, 0)
        progressBar.progress = value
        val animationDuration = 3000

        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, value)
        progressAnimator.duration = animationDuration.toLong()
        progressAnimator.start()
    }

    private fun TypedArray.setItemFormattedValue(styleable: Int, textView: TextView) {
        val value = getInt(styleable, 0)
        textView.text = String.format("%03d", value)
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

    private fun TypedArray.setAllValues() {
        setItemFormattedValue(
            R.styleable.FeatureStatsWidget_hpValue,
            binding.stateHp.tvStateItemValue
        )
        setItemFormattedValue(
            R.styleable.FeatureStatsWidget_attackValue,
            binding.stateAttack.tvStateItemValue
        )
        setItemFormattedValue(
            R.styleable.FeatureStatsWidget_defenseValue,
            binding.stateDefense.tvStateItemValue
        )
        setItemFormattedValue(
            R.styleable.FeatureStatsWidget_specialAttackValue,
            binding.stateSpecialAttack.tvStateItemValue
        )
        setItemFormattedValue(
            R.styleable.FeatureStatsWidget_specialDefenseValue,
            binding.stateSpecialDefense.tvStateItemValue
        )
        setItemFormattedValue(
            R.styleable.FeatureStatsWidget_speedValue,
            binding.stateSpeed.tvStateItemValue
        )
    }

    private fun TypedArray.setAllProgressValues() {
        setProgressValue(R.styleable.FeatureStatsWidget_hpValue, binding.stateHp.progressBar)
        setProgressValue(
            R.styleable.FeatureStatsWidget_attackValue,
            binding.stateAttack.progressBar
        )
        setProgressValue(
            R.styleable.FeatureStatsWidget_defenseValue,
            binding.stateDefense.progressBar
        )
        setProgressValue(
            R.styleable.FeatureStatsWidget_specialAttackValue,
            binding.stateSpecialAttack.progressBar
        )
        setProgressValue(
            R.styleable.FeatureStatsWidget_specialDefenseValue,
            binding.stateSpecialDefense.progressBar
        )
        setProgressValue(R.styleable.FeatureStatsWidget_speedValue, binding.stateSpeed.progressBar)
    }
}