package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.series_details.view.SeriesDetailsListener

class EventsAdapter(itemsEvents: List<EventResult?>, listener: SeriesDetailsListener):
    BaseAdapter<EventResult?>(itemsEvents, listener) {

    override val layoutId: Int = R.layout.series_details_event_item
}