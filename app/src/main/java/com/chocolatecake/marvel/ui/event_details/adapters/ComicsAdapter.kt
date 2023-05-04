package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.event_details.EventDetailsListener

class ComicsAdapter(list: List<ComicsResult?>, listener: EventDetailsListener) :
    BaseAdapter<ComicsResult?>(list, listener) {
    override val layoutId: Int
        get() = R.layout.item_event_comics
}