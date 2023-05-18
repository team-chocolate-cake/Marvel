package com.chocolatecake.marvel.ui.search.model

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.Series

data class SearchDataHolder(
    val series: List<Series> = emptyList(),
    val comics: List<Comic> = emptyList(),
    val characters: List<Character> = emptyList(),
) {
    fun isNotEmpty() = !isEmpty()

    fun isEmpty() = series.isEmpty() &&
            comics.isEmpty() &&
            characters.isEmpty()
}


