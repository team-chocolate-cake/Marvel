package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.event_details.EventDetailsListener

class SeriesAdapter(list: List<SeriesResult?>, listener: EventDetailsListener) :
    BaseAdapter<SeriesResult?>(list, listener) {
    override val layoutId: Int
        get() = R.layout.item_event_series
}
