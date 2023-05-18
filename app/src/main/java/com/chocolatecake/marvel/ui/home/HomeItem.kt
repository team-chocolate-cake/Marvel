package com.chocolatecake.marvel.ui.home

import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.domain.model.Series
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_COMIC
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_HEADER
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_SERIES

sealed class HomeItem(val priority: Int) {
    data class EventsItem(val eventDto: List<Event>) : HomeItem(VIEW_TYPE_HEADER)
    data class SeriesItem(val seriesDto: List<Series>) : HomeItem(VIEW_TYPE_SERIES)
    data class ComicItem(val comicResult: Comic) : HomeItem(VIEW_TYPE_COMIC)
}