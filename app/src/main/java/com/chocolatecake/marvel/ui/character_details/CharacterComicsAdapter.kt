package com.chocolatecake.marvel.ui.character_details

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class CharacterComicsAdapter(
    list: List<ComicsResult>, listener: BaseAdapter.BaseInteractionListener,

) : BaseAdapter<ComicsResult>(list, listener) {
    override val layoutId: Int
        get() = R.layout.item_marvel
}