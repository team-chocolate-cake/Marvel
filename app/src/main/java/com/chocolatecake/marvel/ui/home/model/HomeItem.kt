package com.chocolatecake.marvel.ui.home.model

import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class HomeItem(val priority: Int){
    data class EventsItem(val eventResult: List<EventResult?>): HomeItem(0)
    data class SeriesItem(val seriesResult: List<SeriesResult?>): HomeItem(1)
}