package com.chocolatecake.marvel.ui.home

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_COMIC
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_HEADER
import com.chocolatecake.marvel.ui.home.adapter.HomeAdapter.Companion.VIEW_TYPE_SERIES

sealed class HomeItem(val priority: Int){
    data class EventsItem(val eventDto: List<EventDto?>) : HomeItem(VIEW_TYPE_HEADER)
    data class SeriesItem(val seriesDto: List<SeriesDto?>) : HomeItem(VIEW_TYPE_SERIES)
    data class ComicItem(val comicResult: ComicDto) : HomeItem(VIEW_TYPE_COMIC)
}