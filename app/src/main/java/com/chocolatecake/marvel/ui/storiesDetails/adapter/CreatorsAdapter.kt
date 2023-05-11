package com.chocolatecake.marvel.ui.storiesDetails.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class CreatorsAdapter (creatorList: List<ProfileResult>, listener: SeriesListener) :
    BaseAdapter<ProfileResult?>(creatorList, listener) {
    override val layoutId = R.layout.item_character

}