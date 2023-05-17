package com.chocolatecake.marvel.domain.mapper.character

import com.chocolatecake.marvel.data.local.entities.CharacterEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Character

class CharacterUIMapper : Mapper<CharacterEntity, Character> {

    override fun map(input: CharacterEntity): Character {

        return Character(
            id = input.id,
            imageURL = input.imageURL,
            name = input.name
        )
    }
}