package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.ui.base.BaseAdapter
interface SearchInteractionListener : BaseAdapter.BaseInteractionListener {
    fun onclickSeries(seriesId:Int?)
    fun onclickComics(comicsId:Int?)
    fun onclickCharacters(charactersId:Int?)
}