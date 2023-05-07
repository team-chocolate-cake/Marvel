package com.chocolatecake.marvel.ui.event_details.model

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class EventDetailsItem(val type: EventDetailsItemType, val priority: Int) {
    data class Header(val eventResult: EventResult?) :
        EventDetailsItem(EventDetailsItemType.HEADER, 0)

    data class Character(val profileResult: List<ProfileResult?>) :
        EventDetailsItem(EventDetailsItemType.CHARACTER, 1)

    data class Series(val seriesResult: List<SeriesResult?>) :
        EventDetailsItem(EventDetailsItemType.SERIES, 2)

    data class Comics(val comicsResult: List<ComicsResult?>) :
        EventDetailsItem(EventDetailsItemType.COMICS, 3)
}

enum class EventDetailsItemType {
    HEADER,
    CHARACTER,
    SERIES,
    COMICS
}
