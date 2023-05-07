package com.chocolatecake.marvel.ui.search

data class SearchQuery(
    var query: String? = null,
    val type: SearchItemType = SearchItemType.TYPE_SERIES
)
