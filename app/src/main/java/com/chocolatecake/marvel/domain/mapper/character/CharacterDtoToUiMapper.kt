package com.chocolatecake.marvel.domain.mapper.character

import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Character
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterDtoToUiMapper @Inject constructor() : Mapper<ProfileDto, Character> {
    override fun map(input: ProfileDto): Character {
        return Character(
            id = input.id ?: 0,
            name = input.name ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}