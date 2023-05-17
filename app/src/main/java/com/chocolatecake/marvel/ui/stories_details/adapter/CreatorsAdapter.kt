package com.chocolatecake.marvel.ui.stories_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.CreatorsListener

class CreatorsAdapter(creatorList: List<ProfileDto>, listener: CreatorsListener) :
    BaseAdapter<ProfileDto>(creatorList, listener) {

    override val layoutId = R.layout.item_creator
}