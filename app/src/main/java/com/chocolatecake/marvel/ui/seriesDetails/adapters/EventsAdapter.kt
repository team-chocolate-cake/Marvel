package com.chocolatecake.marvel.ui.seriesDetails.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.seriesDetails.SeriesDetailsListener

class EventsAdapter(itemsEvents: List<EventResult?>, listener: SeriesDetailsListener):
BaseAdapter<EventResult?>(itemsEvents, listener) {
    override val layoutId: Int
        get() = R.layout.event_item
}