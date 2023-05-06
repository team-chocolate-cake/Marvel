package com.chocolatecake.marvel.ui.comic_details.data

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class ComicDetailsItem(val type: ComicDetailsItemType) {
    data class Header(val comic: ComicsResult) : ComicDetailsItem(ComicDetailsItemType.HEADER)
    data class Characters(val list: List<SeriesResult?>) :
        ComicDetailsItem(ComicDetailsItemType.CHARACTERS)
//    data class Events(val list:List<>) : ComicDetailsItem(ComicDetailsItemType.EVENTS)
}

enum class ComicDetailsItemType {
    HEADER,
    CHARACTERS,
    EVENTS
}
