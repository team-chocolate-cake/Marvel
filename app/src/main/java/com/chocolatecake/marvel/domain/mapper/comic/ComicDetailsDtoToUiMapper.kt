package com.chocolatecake.marvel.domain.mapper.comic

import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.ComicDetails
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ComicDetailsDtoToUiMapper @Inject constructor(): Mapper<ComicDto, ComicDetails> {
    override fun map(input: ComicDto): ComicDetails {
        return ComicDetails(
            id= input.id ?: 0,
            title = input.title ?: "",
            description = input.getTextObject() ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}