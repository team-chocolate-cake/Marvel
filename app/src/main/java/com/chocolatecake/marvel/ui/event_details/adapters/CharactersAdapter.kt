package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.event_details.view.EventDetailsListener

class CharactersAdapter(list: List<ProfileResult>, listener: EventDetailsListener) :
    BaseAdapter<ProfileResult>(list, listener) {

    override val layoutId = R.layout.item_character
}
