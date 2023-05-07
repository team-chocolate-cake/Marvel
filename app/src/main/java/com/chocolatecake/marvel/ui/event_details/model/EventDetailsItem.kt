package com.chocolatecake.marvel.ui.event_details.model

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class EventDetailsItem(val type: EventDetailsItemType) {
    data class Header(val eventResult: EventResult?) :
        EventDetailsItem(EventDetailsItemType.HEADER)

    data class Character(val profileResult: List<ProfileResult?>) :
        EventDetailsItem(EventDetailsItemType.CHARACTER)

    data class Series(val seriesResult: List<SeriesResult?>) :
        EventDetailsItem(EventDetailsItemType.SERIES)

    data class Comics(val comicsResult: List<ComicsResult?>) :
        EventDetailsItem(EventDetailsItemType.COMICS)
}

enum class EventDetailsItemType {
    HEADER,
    CHARACTER,
    SERIES,
    COMICS
}
