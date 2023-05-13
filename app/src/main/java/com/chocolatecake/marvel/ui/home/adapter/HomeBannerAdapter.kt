package com.chocolatecake.marvel.ui.home.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class HomeBannerAdapter(itemsBanner: List<EventResult?>, listener: HomeListener) :
    BaseAdapter<EventResult?>(itemsBanner, listener) {
    override val layoutId: Int = R.layout.home_item_banner

}