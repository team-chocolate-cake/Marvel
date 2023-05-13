package com.chocolatecake.marvel.ui.home.adapter

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.abs

class HomeViewPagerLayoutManager(context: Context) :
    LinearLayoutManager(context, HORIZONTAL, false) {
    override fun scrollHorizontallyBy(
        dx: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        val scrolled = super.scrollHorizontallyBy(dx, recycler, state)
        val midpoint = width / 2f
        val initialDistance = 0f
        val maxDistance = midpoint * 0.5f
        val maxScale = 1f
        val minScale = 0.9f

        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
            val d = maxDistance.coerceAtMost(abs(midpoint - childMidpoint))
            val scale = maxScale + (minScale - maxScale) * (d - initialDistance) / (maxDistance - initialDistance)
            child.scaleX = scale
            child.scaleY = scale
        }
        return scrolled
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }
}