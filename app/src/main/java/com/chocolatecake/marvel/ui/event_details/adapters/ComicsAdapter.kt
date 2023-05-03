package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class ComicsAdapter(list: List<ComicsResult>, listener: ComicsListener) :
    BaseAdapter<ComicsResult>(list, listener) {
    override val layoutId: Int
        get() = R.layout.character_view
}

interface ComicsListener : BaseAdapter.BaseInteractionListener {
    fun onClickComics(comicsId: Int?)
}