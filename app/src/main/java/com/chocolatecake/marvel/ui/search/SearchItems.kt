package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class  SearchItems(val searchItemType:SearchItemType ){
 data class  HeaderItem(val viewModel : SearchViewModel) : SearchItems(SearchItemType.TYPE_Header)
 data class  ComicItem(val comic: ComicsResult) : SearchItems(SearchItemType.TYPE_COMICS)
 data class CharacterItem(val character: ProfileResult) : SearchItems(SearchItemType.TYPE_CHARACTER)
 data class SeriesItem(val series: SeriesResult) : SearchItems(SearchItemType.TYPE_SERIES)
}
enum class SearchItemType {
 TYPE_COMICS, TYPE_CHARACTER, TYPE_SERIES, TYPE_Header;
}

