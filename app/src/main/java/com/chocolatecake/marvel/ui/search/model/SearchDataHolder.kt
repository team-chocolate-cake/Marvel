package com.chocolatecake.marvel.ui.search.model

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

data class SearchDataHolder(
    val series: List<SeriesResult> = emptyList(),
    val comics: List<ComicsResult> = emptyList(),
    val characters: List<ProfileResult> = emptyList(),
) {
    fun isNotEmpty() = !isEmpty()

    fun isEmpty() = series.isEmpty() &&
            comics.isEmpty() &&
            characters.isEmpty()
}


