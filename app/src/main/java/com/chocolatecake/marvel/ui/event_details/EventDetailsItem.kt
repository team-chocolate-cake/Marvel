package com.chocolatecake.marvel.ui.event_details

import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.EventDetails
import com.chocolatecake.marvel.domain.model.Series

sealed class EventDetailsItem(val type: EventDetailsItemType) {
    data class HeaderDetails(val event: EventDetails) :
        EventDetailsItem(EventDetailsItemType.HEADER)

    data class CharacterDetails(val profile: List<Character>) :
        EventDetailsItem(EventDetailsItemType.CHARACTER)

    data class SeriesDetails(val series: List<Series>) :
        EventDetailsItem(EventDetailsItemType.SERIES)

    data class ComicsDetails(val comic: List<Comic>) :
        EventDetailsItem(EventDetailsItemType.COMICS)
}

enum class EventDetailsItemType { HEADER, CHARACTER, SERIES, COMICS }