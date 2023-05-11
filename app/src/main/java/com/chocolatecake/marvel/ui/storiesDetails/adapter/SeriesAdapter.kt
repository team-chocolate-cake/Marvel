package com.chocolatecake.marvel.ui.storiesDetails.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class SeriesAdapter (seriesList: List<SeriesResult>, listener: SeriesListener) :
    BaseAdapter<SeriesResult?>(seriesList, listener) {
    override val layoutId = R.layout.series_item




}