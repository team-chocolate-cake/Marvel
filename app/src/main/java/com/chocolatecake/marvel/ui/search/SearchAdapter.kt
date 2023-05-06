package com.chocolatecake.marvel.ui.search


import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.data.model.base.SearchResult
class SearchAdapter(
    private var itemsList: List<SearchResult>,
    listener: SearchInteractionListener
) : BaseAdapter<SearchResult>(itemsList, listener) {
    override val layoutId: Int
        get() = R.layout.item_character

    fun updateList(newItems: List<SearchResult>) {
        itemsList = newItems
        notifyDataSetChanged()
    }
}