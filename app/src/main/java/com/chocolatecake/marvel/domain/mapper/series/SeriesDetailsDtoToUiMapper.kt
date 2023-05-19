package com.chocolatecake.marvel.domain.mapper.series

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.ComicDetails
import com.chocolatecake.marvel.domain.model.SeriesDetails
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SeriesDetailsDtoToUiMapper @Inject constructor() : Mapper<SeriesDto, SeriesDetails> {
    override fun map(input: SeriesDto): SeriesDetails {
        return SeriesDetails(
            id = input.id ?: 0,
            title = input.title ?: "",
            description = input.description ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}