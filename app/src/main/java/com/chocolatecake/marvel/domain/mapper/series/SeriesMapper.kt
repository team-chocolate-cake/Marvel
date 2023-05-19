package com.chocolatecake.marvel.domain.mapper.series

import com.chocolatecake.marvel.data.local.entities.SeriesEntity
import com.chocolatecake.marvel.data.remote.model.dto.SeriesDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SeriesMapper @Inject constructor(): Mapper<SeriesDto, SeriesEntity> {
    override fun map(input: SeriesDto): SeriesEntity {

        return SeriesEntity(
            id = input.id ?: 0,
            description = input.description ?: "",
            title = input.title ?: "",
            imageURL = input.thumbnail?.toUrl() ?: "",
            endYear = input.endYear ?: 0,
            startYear = input.startYear ?: 0
        )
    }
}