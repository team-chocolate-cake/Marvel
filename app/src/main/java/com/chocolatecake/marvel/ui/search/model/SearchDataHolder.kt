package com.chocolatecake.marvel.ui.search.model

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto

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


