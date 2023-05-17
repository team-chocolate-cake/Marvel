package com.chocolatecake.marvel.ui.home.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class HomeBannerAdapter(itemsBanner: List<EventDto?>, listener: HomeListener) :
    BaseAdapter<EventDto?>(itemsBanner, listener) {
    override val layoutId: Int = R.layout.home_item_banner

}