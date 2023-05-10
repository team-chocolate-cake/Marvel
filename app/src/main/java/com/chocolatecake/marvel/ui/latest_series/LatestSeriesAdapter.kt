package com.chocolatecake.marvel.ui.latest_series

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class LatestSeriesAdapter(seriesItems: List<SeriesResult>, listener: SeriesListener) :
    BaseAdapter<SeriesResult>(seriesItems, listener) {

    override val layoutId = R.layout.item_series
}