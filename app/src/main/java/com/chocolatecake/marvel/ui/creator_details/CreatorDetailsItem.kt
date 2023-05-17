package com.chocolatecake.marvel.ui.creator_details

import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.data.remote.model.SeriesDto

sealed class CreatorDetailsItem(val Priority: Int) {
    data class CreatorItem(val creatorResult: ProfileDto?):CreatorDetailsItem(0)
    data class SeriesItem(val seriesDto: List<SeriesDto?>):CreatorDetailsItem(1)
    data class ComicItem(val comicResult: List<ComicDto?>):CreatorDetailsItem(2)
}