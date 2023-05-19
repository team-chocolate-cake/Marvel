package com.chocolatecake.marvel.ui.stories_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class SeriesAdapter (seriesList: List<Series>, listener: SeriesListener) :
    BaseAdapter<Series>(seriesList, listener) {

    override val layoutId = R.layout.series_item
}