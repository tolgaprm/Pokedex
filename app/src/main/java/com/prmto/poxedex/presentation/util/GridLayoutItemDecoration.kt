package com.prmto.poxedex.presentation.util

import android.graphics.Rect
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.recyclerview.widget.RecyclerView
import com.prmto.poxedex.R

class GridLayoutItemDecoration(
    private val spanCount: Int
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == RecyclerView.NO_POSITION) return

        val resources = view.context.resources

        val twoLevelMargin = resources.getDimension(R.dimen.two_level_margin).toInt()
        val sixLevelMargin = resources.getDimension(R.dimen.six_level_margin).toInt()
        val threeLevelMargin = resources.getDimension(R.dimen.three_level_margin).toInt()

        val marginTop = if (itemPosition < spanCount) sixLevelMargin else twoLevelMargin
        val marginLeft = if (itemPosition % spanCount == 0) threeLevelMargin else twoLevelMargin
        val marginRight = if (itemPosition % spanCount == spanCount - 1) threeLevelMargin else 0

        val marginLayoutParams = view.layoutParams as MarginLayoutParams
        marginLayoutParams.setMargins(
            /* left = */ marginLeft,
            /* top = */ marginTop,
            /* right = */ marginRight,
            /* bottom = */ 0
        )
        view.layoutParams = marginLayoutParams
    }
}