package com.chocolatecake.marvel.domain.mapper.character

import com.chocolatecake.marvel.data.local.model.CharacterEntity
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.domain.mapper.Mapper

class CharacterMapper : Mapper<ProfileDto, CharacterEntity> {

    override fun map(input: ProfileDto): CharacterEntity {
        return CharacterEntity(
            id= input.id ?: 0,
            name = input.name ?: "",
            description = input.description ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}