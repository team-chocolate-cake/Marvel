package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class  SearchItems(val searchItemType:SearchItemType ){
 data class SeriesItem(val series: SeriesResult) : SearchItems(SearchItemType.TYPE_SERIES)
}
enum class SearchItemType {
  TYPE_SERIES;
}

