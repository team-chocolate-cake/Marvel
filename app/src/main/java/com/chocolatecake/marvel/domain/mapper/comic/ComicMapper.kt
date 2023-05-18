package com.chocolatecake.marvel.domain.mapper.comic

import com.chocolatecake.marvel.data.local.entities.ComicsEntity
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ComicMapper @Inject constructor() : Mapper<ComicDto, ComicsEntity> {

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