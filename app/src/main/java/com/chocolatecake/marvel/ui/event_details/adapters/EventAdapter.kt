package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.base.BaseAdapter.BaseInteractionListener

class EventAdapter(list: List<ProfileResult>, listener: CharacterListener) :
    BaseAdapter<ProfileResult>(list, listener) {
    override val layoutId: Int
        get() = R.layout.character_view
}
