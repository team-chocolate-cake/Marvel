package com.chocolatecake.marvel.domain.mapper.series

import com.chocolatecake.marvel.data.local.entities.SeriesEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Series
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class SeriesUIMapper @Inject constructor() : Mapper<SeriesEntity, Series> {
    override fun map(input: SeriesEntity): Series {
        return Series(
            id = input.id,
            imageURL = input.imageURL,
            title = input.title
        )
    }
}