package com.chocolatecake.marvel.ui.stories_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.domain.model.Creator
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.CreatorsListener

class CreatorsAdapter(creatorList: List<Creator>, listener: CreatorsListener) :
    BaseAdapter<Creator>(creatorList, listener) {

    override val layoutId = R.layout.item_creator
}