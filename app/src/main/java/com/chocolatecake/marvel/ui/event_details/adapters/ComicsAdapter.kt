package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class ComicsAdapter(list: List<ComicsResult>, listener: EventDetailsListener) :
    BaseAdapter<ComicsResult>(list, listener) {

    override val layoutId = R.layout.item_event_comics
}