package com.chocolatecake.marvel.domain.mapper.story

import com.chocolatecake.marvel.data.local.model.StoryEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Story

class StoryUIMapper : Mapper<StoryEntity, Story> {

    override fun map(input: StoryEntity): Story {
        return Story(
            id = input.id,
            title = input.title,
            date = input.date
        )
    }
}