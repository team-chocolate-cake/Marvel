package com.chocolatecake.marvel.domain.mapper.comic

import com.chocolatecake.marvel.data.local.entities.ComicsEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Comic

class ComicUIMapper : Mapper<ComicsEntity, Comic> {

    override fun map(input: ComicsEntity): Comic {

        return Comic(
            id = input.id,
            title = input.title,
            imageURL = input.imageURL
        )
    }
}