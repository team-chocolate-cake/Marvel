package com.chocolatecake.marvel.domain.mapper.story

import com.chocolatecake.marvel.data.remote.model.dto.StoryDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.StoryDetails
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class StoryDetailsDtoToUiMapper @Inject constructor(): Mapper<StoryDto, StoryDetails> {
    override fun map(input: StoryDto): StoryDetails {
        return StoryDetails(
            id = input.id ?: 0,
            title = input.title ?: "",
            date = input.date ?: "",
            description = input.description ?: ""
        )
    }
}