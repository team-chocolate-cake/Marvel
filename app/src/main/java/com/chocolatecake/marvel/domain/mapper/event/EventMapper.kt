package com.chocolatecake.marvel.domain.mapper.event

import com.chocolatecake.marvel.data.local.model.EventEntity
import com.chocolatecake.marvel.data.remote.model.EventDto
import com.chocolatecake.marvel.domain.mapper.Mapper

class EventMapper : Mapper<EventDto, EventEntity> {
    override fun map(input: EventDto): EventEntity {

        return EventEntity(
            id = input.id ?: 0,
            description = input.description ?: "",
            title = input.title ?: "",
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}