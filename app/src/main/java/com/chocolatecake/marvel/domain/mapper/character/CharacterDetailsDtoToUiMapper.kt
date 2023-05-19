package com.chocolatecake.marvel.domain.mapper.character

import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.CharacterDetails
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterDetailsDtoToUiMapper @Inject constructor() : Mapper<ProfileDto, CharacterDetails> {
    override fun map(input: ProfileDto): CharacterDetails {
        return CharacterDetails(
            id = input.id ?: 0,
            name = input.name ?: "",
            description = input.description ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}