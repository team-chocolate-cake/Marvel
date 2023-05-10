package com.chocolatecake.marvel.ui.latest_series

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class LatestSeriesAdapter(seriesItems: List<SeriesResult>, listener: LatestSeriesViewModel) :
    BaseAdapter<SeriesResult>(seriesItems, listener) {

    override val layoutId = R.layout.item_series
}