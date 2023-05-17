package com.chocolatecake.marvel.ui.series_details

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto

sealed class SeriesDetailsItem(val type: SeriesDetailsItemType) {

    data class SeriesItem(val seriesDto: SeriesDto?) :
        SeriesDetailsItem(SeriesDetailsItemType.HEADER)

    data class CharactersItem(val charactersResult: List<ProfileDto?>) :
        SeriesDetailsItem(SeriesDetailsItemType.CHARACTERS)

    data class EventsItem(val eventDto: List<EventDto?>) :
        SeriesDetailsItem(SeriesDetailsItemType.EVENTS)

    data class ComicsItem(val comicDto: List<ComicDto?>) :
        SeriesDetailsItem(SeriesDetailsItemType.COMICS)

}
enum class SeriesDetailsItemType { HEADER, CHARACTERS, EVENTS, COMICS }
