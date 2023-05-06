package com.chocolatecake.marvel.ui.home.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.home.view.HomeListener

class SeriesAdapter(itemsSeries: List<SeriesResult?>, listener: HomeListener) :
    BaseAdapter<SeriesResult?>(itemsSeries, listener) {
    override val layoutId: Int
        get() = R.layout.home_item_series
}