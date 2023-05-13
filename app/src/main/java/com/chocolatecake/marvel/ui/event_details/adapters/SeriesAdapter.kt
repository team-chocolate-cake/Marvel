package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SeriesAdapter(list: List<SeriesResult>, listener: EventDetailsListener) :
    BaseAdapter<SeriesResult>(list, listener) {

    override val layoutId= R.layout.item_event_series
}