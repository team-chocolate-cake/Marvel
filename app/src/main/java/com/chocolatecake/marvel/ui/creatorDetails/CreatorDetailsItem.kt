package com.chocolatecake.marvel.ui.creatorDetails

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

sealed class CreatorDetailsItem(val Priority: Int) {
    data class CreatorItem(val creatorResult: ProfileResult?):CreatorDetailsItem(0)
    data class SeriesItem(val seriesResult: List<SeriesResult?>):CreatorDetailsItem(1)
    data class ComicItem(val comicResult: List<ComicsResult?>):CreatorDetailsItem(2)
}