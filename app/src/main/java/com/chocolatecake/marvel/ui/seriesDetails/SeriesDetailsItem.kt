package com.chocolatecake.marvel.ui.seriesDetails

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class SeriesDetailsItem(val priority: Int){

    data class SeriesItem(val seriesResult: SeriesResult?): SeriesDetailsItem(0)
    data class CharactersItem(val charactersResult: List<ProfileResult?>): SeriesDetailsItem(1)
    data class EventsItem(val eventResult: List<EventResult?>): SeriesDetailsItem(2)
    data class ComicsItem(val comicsResult: List<ComicsResult?>): SeriesDetailsItem(3)
}
