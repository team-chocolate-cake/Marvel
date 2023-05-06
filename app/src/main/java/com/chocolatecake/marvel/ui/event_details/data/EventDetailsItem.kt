package com.chocolatecake.marvel.ui.event_details.data

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class EventDetailsItem(val priority: Int) {
    data class Header(val eventResult: EventResult?) : EventDetailsItem(0)
    data class Character(val profileResult: List<ProfileResult?>) : EventDetailsItem(1)
    data class Series(val seriesResult: List<SeriesResult?>) :EventDetailsItem(2)
    data class Comics( val comicsResult: List<ComicsResult?>) :EventDetailsItem(3)
}

/*
enum class EventDetailsItemType {
    HEADER,
    CHARACTER,
    SERIES,
    COMICS
}*/
