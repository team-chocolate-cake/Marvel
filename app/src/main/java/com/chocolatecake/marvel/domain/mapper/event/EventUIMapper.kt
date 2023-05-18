package com.chocolatecake.marvel.domain.mapper.event

import com.chocolatecake.marvel.data.local.entities.EventEntity
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Event
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject
@ViewModelScoped
class EventUIMapper @Inject constructor() : Mapper<EventEntity, Event> {
    override fun map(input: EventEntity): Event {

        return Event(
            id = input.id,
            imageURL = input.imageURL
        )
    }
}