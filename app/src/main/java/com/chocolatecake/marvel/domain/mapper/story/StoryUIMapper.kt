package com.chocolatecake.marvel.domain.mapper.story

import com.chocolatecake.marvel.data.local.entities.StoryEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Story
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class StoryUIMapper @Inject constructor() : Mapper<StoryEntity, Story> {

    override fun map(input: StoryEntity): Story {
        return Story(
            id = input.id,
            title = input.title,
            date = input.date
        )
    }
}