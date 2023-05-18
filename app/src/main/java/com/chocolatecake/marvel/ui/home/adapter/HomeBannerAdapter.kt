package com.chocolatecake.marvel.ui.home.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.ui.base.BaseAdapter

class HomeBannerAdapter(itemsBanner: List<Event>, listener: HomeListener) :
    BaseAdapter<Event>(itemsBanner, listener) {
    override val layoutId: Int = R.layout.home_item_banner

}