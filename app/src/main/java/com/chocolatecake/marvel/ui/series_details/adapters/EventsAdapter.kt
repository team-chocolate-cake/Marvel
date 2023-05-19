package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.ui.base.BaseAdapter

class EventsAdapter(itemsEvents: List<Event?>, listener: SeriesDetailsListener):
    BaseAdapter<Event?>(itemsEvents, listener) {

    override val layoutId: Int = R.layout.series_details_event_item
}