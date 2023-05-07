package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

data class SearchUiState(
    val series: List<SeriesResult> = emptyList(),
    val comics: List<ComicsResult> = emptyList(),
    val characters: List<ProfileResult> = emptyList(),
)
