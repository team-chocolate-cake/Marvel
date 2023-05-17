package com.chocolatecake.marvel.ui.stories_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class SeriesAdapter (seriesList: List<SeriesDto>, listener: SeriesListener) :
    BaseAdapter<SeriesDto?>(seriesList, listener) {

    override val layoutId = R.layout.series_item
}