package com.prmto.poxedex.presentation.util

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()

        val isNotLoadingAndNotLastPage = !isLoading() && !isLastPage()
        val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount

        if (isNotLoadingAndNotLastPage && isAtLastItem && firstVisibleItemPosition >= 0) {
            loadMoreItems()
        }
    }

    protected abstract fun loadMoreItems()
    abstract fun isLastPage(): Boolean
    abstract fun isLoading(): Boolean
}