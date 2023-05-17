package com.chocolatecake.marvel.ui.latest_series

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class LatestSeriesAdapter(seriesItems: List<SeriesDto>, listener: SeriesListener) :
    BaseAdapter<SeriesDto>(seriesItems, listener) {

    override val layoutId = R.layout.item_series
}