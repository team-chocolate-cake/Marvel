package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.SeriesDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SeriesAdapter(list: List<SeriesDto>, listener: EventDetailsListener) :
    BaseAdapter<SeriesDto>(list, listener) {

    override val layoutId= R.layout.item_event_series
}