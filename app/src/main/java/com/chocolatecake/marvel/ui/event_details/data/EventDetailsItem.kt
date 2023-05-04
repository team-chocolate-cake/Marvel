package com.chocolatecake.marvel.ui.event_details.data

import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.ui.event_details.EventDetailsViewModel

sealed class EventDetailsItem(val type: EventDetailsItemType) {

    data class Header(val eventResult: EventResult?) :
        EventDetailsItem(EventDetailsItemType.HEADER)

    data class Character(val viewModel: EventDetailsViewModel ) :
        EventDetailsItem(EventDetailsItemType.CHARACTER)

    data class Series(val  viewModel: EventDetailsViewModel) :
        EventDetailsItem(EventDetailsItemType.SERIES)

    data class Comics(val viewModel: EventDetailsViewModel) :
        EventDetailsItem(EventDetailsItemType.COMICS)
}

enum class EventDetailsItemType {
    HEADER,
    CHARACTER,
    SERIES,
    COMICS
}