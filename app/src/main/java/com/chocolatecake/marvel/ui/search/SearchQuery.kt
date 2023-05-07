package com.chocolatecake.marvel.ui.search

data class SearchQuery(
    var query: String = "",
    val type: SearchItemType = SearchItemType.TYPE_SERIES
)
