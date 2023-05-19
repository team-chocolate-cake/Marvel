package com.chocolatecake.marvel.domain.mapper.comic

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Comic
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ComicDtoToUIMapper @Inject constructor(): Mapper<ComicDto,Comic> {
    override fun map(input: ComicDto): Comic {
        return Comic(
            id = input.id ?: 0,
                title = input.title ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}