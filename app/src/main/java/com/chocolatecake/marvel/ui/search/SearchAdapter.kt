package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.ui.base.BaseAdapter

class SearchAdapter(
    itemsList: MutableList<SearchType>,
    listener:SearchInteractionListener
    ):BaseAdapter<SearchType>(itemsList,listener) {
    override val layoutId: Int
        get() = R.layout.item_marvel
}
interface SearchInteractionListener : BaseAdapter.BaseInteractionListener {
    fun onClick(searchType:SearchType)
}
