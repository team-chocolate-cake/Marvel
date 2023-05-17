package com.chocolatecake.marvel.ui.home.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class HomeSeriesAdapter(itemsSeries: List<SeriesDto?>, listener: HomeListener) :
    BaseAdapter<SeriesDto?>(itemsSeries, listener) {
    override val layoutId: Int = R.layout.home_item_series
}