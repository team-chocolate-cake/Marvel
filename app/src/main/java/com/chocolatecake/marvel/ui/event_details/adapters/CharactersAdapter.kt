package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.ui.base.BaseAdapter

class CharactersAdapter(list: List<Character>, listener: EventDetailsListener) :
    BaseAdapter<Character>(list, listener) {

    override val layoutId = R.layout.item_character
}
