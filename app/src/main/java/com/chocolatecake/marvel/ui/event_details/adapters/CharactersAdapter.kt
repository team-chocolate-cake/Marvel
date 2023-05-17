package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class CharactersAdapter(list: List<ProfileDto>, listener: EventDetailsListener) :
    BaseAdapter<ProfileDto>(list, listener) {

    override val layoutId = R.layout.item_character
}
