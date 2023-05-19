package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.ui.base.BaseAdapter

class ComicsAdapter(list: List<Comic>, listener: EventDetailsListener) :
    BaseAdapter<Comic>(list, listener) {

    override val layoutId = R.layout.item_event_comics
}