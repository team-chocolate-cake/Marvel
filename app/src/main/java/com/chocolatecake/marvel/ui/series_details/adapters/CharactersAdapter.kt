package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class CharactersAdapter(itemsCharacters: List<ProfileDto?>, listener: SeriesDetailsListener) :
    BaseAdapter<ProfileDto?>(itemsCharacters, listener) {

    override val layoutId: Int = R.layout.series_details_character_item
}