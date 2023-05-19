package com.chocolatecake.marvel.ui.series_details

import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.Event
import com.chocolatecake.marvel.domain.model.SeriesDetails

sealed class SeriesDetailsItem(val type: SeriesDetailsItemType) {

    data class SeriesItem(val series: SeriesDetails?) :
        SeriesDetailsItem(SeriesDetailsItemType.HEADER)

    data class CharactersItem(val charactersResult: List<Character?>) :
        SeriesDetailsItem(SeriesDetailsItemType.CHARACTERS)

    data class EventsItem(val event: List<Event?>) :
        SeriesDetailsItem(SeriesDetailsItemType.EVENTS)

    data class ComicsItem(val comic: List<Comic?>) :
        SeriesDetailsItem(SeriesDetailsItemType.COMICS)

}
enum class SeriesDetailsItemType { HEADER, CHARACTERS, EVENTS, COMICS }
