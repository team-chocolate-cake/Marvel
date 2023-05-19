package com.chocolatecake.marvel.domain.mapper.event

import com.chocolatecake.marvel.data.local.entities.EventEntity
import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.EventDetails
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class EventDtoToEventDetailsMapper  @Inject constructor(): Mapper<EventDto, EventDetails> {
    override fun map(input: EventDto): EventDetails {

        return EventDetails(
            id = input.id ?: 0,
            description = input.description ?: "",
            title = input.title ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}