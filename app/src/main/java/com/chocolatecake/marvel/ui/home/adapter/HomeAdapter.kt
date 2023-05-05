package com.chocolatecake.marvel.ui.home.adapter

import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.home.model.HomeItem
import com.chocolatecake.marvel.ui.home.view.HomeListener

class HomeAdapter(private var itemsHome: MutableList<HomeItem>, private val listener: HomeListener) :
    BaseAdapter<HomeItem>(itemsHome, listener) {
    override val layoutId: Int
        get() = 0

    fun setItem(item: HomeItem) {
        val newItems = itemsHome.apply {
            item.priority.takeIf { it != 2 }?.run {
                removeAt(item.priority)
            }
            add(item.priority, item)
        }
        setItems(newItems)
    }


}