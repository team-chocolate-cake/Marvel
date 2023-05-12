package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.series_details.view.SeriesDetailsListener

class CharactersAdapter(itemsCharacters: List<ProfileResult?>, listener: SeriesDetailsListener) :
    BaseAdapter<ProfileResult?>(itemsCharacters, listener) {

    override val layoutId: Int = R.layout.series_details_character_item
}