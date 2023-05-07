package com.chocolatecake.marvel.ui.home.model

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class HomeItem(val priority: Int){
    data class EventsItem(val eventResult: List<EventResult?>) : HomeItem(VIEW_TYPE_HEADER)
    data class SeriesItem(val seriesResult: List<SeriesResult?>) : HomeItem(VIEW_TYPE_SERIES)
    data class ComicItem(val comicResult: ComicsResult) : HomeItem(VIEW_TYPE_COMIC)

    companion object {
        private const val VIEW_TYPE_HEADER = 0
        private const val VIEW_TYPE_SERIES = 1
        private const val VIEW_TYPE_COMIC = 2
    }
}