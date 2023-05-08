package com.chocolatecake.marvel.ui.search.model

import com.chocolatecake.marvel.ui.search.model.SearchItemType

data class SearchQuery(
    var query: String? = null,
    val type: SearchItemType = SearchItemType.TYPE_SERIES
)
