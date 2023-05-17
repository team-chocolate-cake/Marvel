package com.chocolatecake.marvel.ui.creator_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SeriesAdapter(seriesList: List<SeriesDto>, listener: CreatorDetailsListener,):
    BaseAdapter<SeriesDto?>(seriesList,listener) {

    override val layoutId = R.layout.series_item
}