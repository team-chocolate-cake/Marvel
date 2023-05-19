package com.chocolatecake.marvel.domain.mapper.character

import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.data.remote.model.dto.ProfileDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class CharacterMapper @Inject constructor() : Mapper<ProfileDto, CharacterEntity> {

    override fun map(input: ProfileDto): CharacterEntity {
        return CharacterEntity(
            id = input.id ?: 0,
            name = input.name ?: "",
            description = input.description ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}