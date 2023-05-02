package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class SearchType(val type: SearchItemType) {

    data class ComicItem(val comic: ComicsResult) : SearchType(
        SearchItemType.TYPE_COMICS
    )

    data class CharacterItem(val character: ProfileResult) :
        SearchType(SearchItemType.TYPE_CHARACTER)

    data class SeriesItem(val series: SeriesResult) :
        SearchType(SearchItemType.TYPE_SERIES)
}


enum class SearchItemType {
  TYPE_COMICS, TYPE_CHARACTER, TYPE_SERIES;
    companion object {
        fun createStatus(status: Int): SearchItemType {
            return when (status) {
                0 -> TYPE_CHARACTER
                1 -> TYPE_SERIES
                else -> TYPE_COMICS
            }
        }
    }
}
