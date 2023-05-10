package com.chocolatecake.marvel.ui.creatorDetails.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.creatorDetails.CreatorDetailsListener

class SeriesAdapter(seriesList: List<SeriesResult>, listener: CreatorDetailsListener):
    BaseAdapter<SeriesResult?>(seriesList,listener) {
    override val layoutId = R.layout.series_item
}