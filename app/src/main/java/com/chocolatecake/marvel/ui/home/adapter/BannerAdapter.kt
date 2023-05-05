package com.chocolatecake.marvel.ui.home.view.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.home.view.HomeListener

class BannerAdapter(itemsBanner: List<EventResult?>,listener: HomeListener) :
    BaseAdapter<EventResult?>(itemsBanner, listener) {
    override val layoutId: Int
        get() = R.layout.item_banner

}