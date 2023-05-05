package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class  SearchItems(val searchItemType:SearchItemType ){
 data class SeriesItem(val series: SeriesResult) : SearchItems(SearchItemType.TYPE_SERIES)
    data class ComicsItem(val comics: ComicsResult) : SearchItems(SearchItemType.TYPE_Comics)

    data class CharacterItem(val character: ProfileResult) : SearchItems(SearchItemType.TYPE_Character)

}
enum class SearchItemType {
  TYPE_SERIES,TYPE_Comics,TYPE_Character;
}

