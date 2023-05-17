package com.chocolatecake.marvel.ui.series_details

import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.data.remote.model.EventDto
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.data.remote.model.SeriesDto

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
