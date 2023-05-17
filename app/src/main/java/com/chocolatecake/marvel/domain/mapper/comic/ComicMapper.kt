package com.chocolatecake.marvel.domain.mapper.comic

import com.chocolatecake.marvel.data.local.model.ComicsEntity
import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.domain.mapper.Mapper

class ComicMapper : Mapper<ComicDto, ComicsEntity> {

    override fun map(input: ComicDto): ComicsEntity {

        return ComicsEntity(
            id = input.id ?: 0,
            description = input.description ?: "",
            title = input.title ?: "",
            imageURL = input.thumbnail?.toUrl() ?: "",
            textObject = input.getTextObject() ?: ""
        )
    }
}