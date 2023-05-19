package com.chocolatecake.marvel.domain.mapper.series

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Comic
import javax.inject.Inject

class ComicsDtoToUiMapper @Inject constructor() : Mapper<ComicDto, Comic> {
    override fun map(input: ComicDto): Comic {
        return Comic(
            id = input.id ?: 0,
            title = input.title ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}