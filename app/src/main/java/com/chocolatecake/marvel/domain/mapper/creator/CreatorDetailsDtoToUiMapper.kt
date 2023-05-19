package com.chocolatecake.marvel.domain.mapper.creator

import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.CreatorDetails
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CreatorDetailsDtoToUiMapper @Inject constructor(): Mapper<ProfileDto, CreatorDetails> {
    override fun map(input: ProfileDto): CreatorDetails {
        return CreatorDetails(
            id= input.id ?: 0,
            name = input.name ?: "",
            description = input.description ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}