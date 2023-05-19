package com.chocolatecake.marvel.ui.creator_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SeriesAdapter(seriesList: List<Series>, listener: CreatorDetailsListener,):
    BaseAdapter<Series>(seriesList,listener) {

    override val layoutId = R.layout.series_item
}