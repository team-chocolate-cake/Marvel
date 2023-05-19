package com.chocolatecake.marvel.domain.mapper.story

import com.chocolatecake.marvel.data.local.entities.StoryEntity
import com.chocolatecake.marvel.data.remote.model.dto.StoryDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class StoryMapper @Inject constructor() : Mapper<StoryDto, StoryEntity> {

    override fun map(input: StoryDto): StoryEntity {
        return StoryEntity(
            id = input.id ?: 0,
            description = input.description ?: "",
            title = input.title ?: "",
            date = input.getFormattedDate() ?: ""
        )
    }
}