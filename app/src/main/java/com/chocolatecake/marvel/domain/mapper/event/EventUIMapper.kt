package com.chocolatecake.marvel.domain.mapper.event

import com.chocolatecake.marvel.data.local.entities.EventEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Event

class EventUIMapper : Mapper<EventEntity, Event> {
    override fun map(input: EventEntity): Event {

        return Event(
            id = input.id,
            imageURL = input.imageURL
        )
    }
}