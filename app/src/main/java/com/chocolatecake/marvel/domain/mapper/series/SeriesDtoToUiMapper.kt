package com.chocolatecake.marvel.domain.mapper.series

import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Series
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SeriesDtoToUiMapper @Inject constructor(): Mapper<SeriesDto, Series> {
    override fun map(input: SeriesDto): Series {
        return Series(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}