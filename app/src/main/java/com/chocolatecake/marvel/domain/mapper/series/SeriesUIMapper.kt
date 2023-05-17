package com.chocolatecake.marvel.domain.mapper.series

import com.chocolatecake.marvel.data.local.model.SeriesEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Series

class SeriesUIMapper : Mapper<SeriesEntity, Series> {
    override fun map(input: SeriesEntity): Series {
        return Series(
            id = input.id,
            imageURL = input.imageURL,
            title = input.title
        )
    }
}