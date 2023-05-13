package com.chocolatecake.marvel.ui.home

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.SeriesResult
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_COMIC
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_HEADER
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_SERIES

sealed class HomeItem(val priority: Int){
    data class EventsItem(val eventResult: List<EventResult?>) : HomeItem(VIEW_TYPE_HEADER)
    data class SeriesItem(val seriesResult: List<SeriesResult?>) : HomeItem(VIEW_TYPE_SERIES)
    data class ComicItem(val comicResult: ComicsResult) : HomeItem(VIEW_TYPE_COMIC)
}