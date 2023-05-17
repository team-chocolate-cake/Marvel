package com.chocolatecake.marvel.domain.mapper.story

import com.chocolatecake.marvel.data.local.model.StoryEntity
import com.chocolatecake.marvel.data.remote.model.StoryDto
import com.chocolatecake.marvel.domain.mapper.Mapper

class StoryMapper : Mapper<StoryDto, StoryEntity> {

    override fun map(input: StoryDto): StoryEntity {
        return StoryEntity(
            id = input.id ?: 0,
            description = input.description ?: "",
            title = input.title ?: "",
            date = input.getFormattedDate() ?: ""
        )
    }
}