package com.chocolatecake.marvel.ui.home

import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class HomeAdapter(itemSeries: List<SeriesResult>, private val listener: HomeListener) :
    BaseAdapter<SeriesResult>(itemSeries, listener) {
    override val layoutId: Int
        get() = TODO("Not yet implemented")
}