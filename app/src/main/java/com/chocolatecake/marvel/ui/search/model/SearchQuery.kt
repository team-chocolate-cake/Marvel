package com.chocolatecake.marvel.ui.search.model


data class SearchQuery(
    var query: String? = null,
    val type: SearchItemType = SearchItemType.TYPE_SERIES
)
