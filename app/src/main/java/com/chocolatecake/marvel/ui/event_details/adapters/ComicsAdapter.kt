package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class ComicsAdapter(list: List<ComicDto>, listener: EventDetailsListener) :
    BaseAdapter<ComicDto>(list, listener) {

    override val layoutId = R.layout.item_event_comics
}