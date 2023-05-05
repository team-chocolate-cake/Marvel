package com.chocolatecake.marvel.ui.seriesDetails.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.seriesDetails.SeriesDetailsListener

class CharactersAdapter(itemsCharacters: List<ProfileResult?>, listener: SeriesDetailsListener) :
    BaseAdapter<ProfileResult?>(itemsCharacters, listener) {
    override val layoutId: Int
        get() = R.layout.character_item


}