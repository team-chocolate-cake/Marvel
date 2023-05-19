package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SeriesAdapter(list: List<Series>, listener: EventDetailsListener) :
    BaseAdapter<Series>(list, listener) {

    override val layoutId= R.layout.item_event_series
}