package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.EventDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class EventsAdapter(itemsEvents: List<EventDto?>, listener: SeriesDetailsListener):
    BaseAdapter<EventDto?>(itemsEvents, listener) {

    override val layoutId: Int = R.layout.series_details_event_item
}