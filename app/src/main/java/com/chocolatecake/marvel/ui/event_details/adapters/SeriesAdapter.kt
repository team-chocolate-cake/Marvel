package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SeriesAdapter(list: List<SeriesResult>, listener: SeriesListener) :
    BaseAdapter<SeriesResult>(list, listener) {
    override val layoutId: Int
        get() = R.layout.series_view
}

interface SeriesListener : BaseAdapter.BaseInteractionListener {
    fun onClickSeries(seriesId: Int?)
}