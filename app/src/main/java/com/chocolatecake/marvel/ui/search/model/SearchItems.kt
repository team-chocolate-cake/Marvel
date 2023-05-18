package com.chocolatecake.marvel.ui.search.model

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.domain.model.Character
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.domain.model.Series

sealed class SearchItems(val searchItemType: SearchItemType) {
    data class SeriesItem(val series: Series) : SearchItems(SearchItemType.TYPE_SERIES)
    data class ComicsItem(val comics: Comic) : SearchItems(SearchItemType.TYPE_COMICS)
    data class CharacterItem(val character: Character) :
        SearchItems(SearchItemType.TYPE_CHARACTER)
}

enum class SearchItemType {
    TYPE_SERIES, TYPE_COMICS, TYPE_CHARACTER
}

