package com.chocolatecake.marvel.ui.search.view

import com.chocolatecake.marvel.ui.base.BaseAdapter
interface SearchInteractionListener : BaseAdapter.BaseInteractionListener {
    fun onclickSeries(id:Int?)
    fun onclickComics(id:Int?)
    fun onclickCharacters(id:Int?)
}