package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class SearchItems(val priority: Int) {
    data class SeriesItem(val series: List<SeriesResult?>) : SearchItems(1)
    data class ComicsItem(val comics: List<ComicsResult?>) : SearchItems(2)
    data class CharacterItem(val character: List<ProfileResult?>) : SearchItems(3)
}
/*enum class SearchItemType {
    TYPE_SERIES, TYPE_COMICS, TYPE_CHARACTER
}*/

