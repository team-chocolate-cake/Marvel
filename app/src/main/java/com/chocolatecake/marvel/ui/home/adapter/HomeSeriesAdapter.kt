package com.chocolatecake.marvel.ui.home.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.base.BaseAdapter

class HomeSeriesAdapter(itemsSeries: List<Series>, listener: HomeListener) :
    BaseAdapter<Series>(itemsSeries, listener) {
    override val layoutId: Int = R.layout.home_item_series
}