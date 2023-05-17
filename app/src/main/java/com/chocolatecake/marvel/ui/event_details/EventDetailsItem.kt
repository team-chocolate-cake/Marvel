package com.chocolatecake.marvel.ui.event_details

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto

sealed class EventDetailsItem(val type: EventDetailsItemType) {
    data class Header(val eventDto: EventDto) :
        EventDetailsItem(EventDetailsItemType.HEADER)

    data class Character(val profileDto: List<ProfileDto>) :
        EventDetailsItem(EventDetailsItemType.CHARACTER)

    data class Series(val seriesDto: List<SeriesDto>) :
        EventDetailsItem(EventDetailsItemType.SERIES)

    data class Comics(val comicDto: List<ComicDto>) :
        EventDetailsItem(EventDetailsItemType.COMICS)
}

enum class EventDetailsItemType { HEADER, CHARACTER, SERIES, COMICS }