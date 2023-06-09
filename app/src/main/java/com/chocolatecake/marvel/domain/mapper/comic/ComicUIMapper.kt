package com.chocolatecake.marvel.domain.mapper.comic

import com.chocolatecake.marvel.data.local.entities.ComicsEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Comic
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ComicUIMapper @Inject constructor() : Mapper<ComicsEntity, Comic> {

    override fun map(input: ComicsEntity): Comic {

        return Comic(
            id = input.id,
            title = input.title,
            imageURL = input.imageURL
        )
    }
}