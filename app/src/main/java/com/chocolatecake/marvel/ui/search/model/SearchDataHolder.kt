package com.chocolatecake.marvel.ui.search.model

import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.data.remote.model.SeriesDto

data class SearchDataHolder(
    val series: List<SeriesDto> = emptyList(),
    val comics: List<ComicDto> = emptyList(),
    val characters: List<ProfileDto> = emptyList(),
) {
    fun isNotEmpty() = !isEmpty()

    fun isEmpty() = series.isEmpty() &&
            comics.isEmpty() &&
            characters.isEmpty()
}


