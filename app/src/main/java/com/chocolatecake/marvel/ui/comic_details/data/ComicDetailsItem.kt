package com.chocolatecake.marvel.ui.comic_details.data

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult

sealed class ComicDetailsItem(val type: ComicDetailsItemType) {
    data class Header(val comic: ComicsResult) : ComicDetailsItem(ComicDetailsItemType.HEADER)
    data class Characters(val list: List<ProfileResult>) :
        ComicDetailsItem(ComicDetailsItemType.CHARACTERS)
     data class Events(val eventResult:EventResult) : ComicDetailsItem(ComicDetailsItemType.EVENTS)
}

enum class ComicDetailsItemType {
    HEADER,
    CHARACTERS,
    EVENTS
}
