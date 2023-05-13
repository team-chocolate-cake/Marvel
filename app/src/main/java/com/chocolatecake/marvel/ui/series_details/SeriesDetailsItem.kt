package com.chocolatecake.marvel.ui.series_details

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class SeriesDetailsItem(val type: SeriesDetailsItemType) {

    data class SeriesItem(val seriesResult: SeriesResult?) :
        SeriesDetailsItem(SeriesDetailsItemType.HEADER)

    data class CharactersItem(val charactersResult: List<ProfileResult?>) :
        SeriesDetailsItem(SeriesDetailsItemType.CHARACTERS)

    data class EventsItem(val eventResult: List<EventResult?>) :
        SeriesDetailsItem(SeriesDetailsItemType.EVENTS)

    data class ComicsItem(val comicsResult: List<ComicsResult?>) :
        SeriesDetailsItem(SeriesDetailsItemType.COMICS)

}
enum class SeriesDetailsItemType { HEADER, CHARACTERS, EVENTS, COMICS }
