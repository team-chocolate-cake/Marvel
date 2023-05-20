package com.chocolatecake.marvel.ui.search.model

import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.Series

data class SearchDataHolder(
    val series: List<Series> = emptyList(),
    val comics: List<Comic> = emptyList(),
    val characters: List<Character> = emptyList(),
) {
    fun isEmpty() = series.isEmpty() && comics.isEmpty() && characters.isEmpty()
}


