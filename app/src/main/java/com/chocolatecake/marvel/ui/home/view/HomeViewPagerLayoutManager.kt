package com.chocolatecake.marvel.ui.home.view

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
        val d0 = 0f
        val d1 = midpoint * 0.5f
        val s0 = 1f
        val s1 = 0.9f
        for (i in 0 until childCount) {
            val child = getChildAt(i)
            val childMidpoint = (getDecoratedRight(child!!) + getDecoratedLeft(child)) / 2f
            val d = d1.coerceAtMost(abs(midpoint - childMidpoint))
            val scale = s0 + (s1 - s0) * (d - d0) / (d1 - d0)
            child?.scaleX = scale
            child?.scaleY = scale
        }
        return scrolled
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }
}