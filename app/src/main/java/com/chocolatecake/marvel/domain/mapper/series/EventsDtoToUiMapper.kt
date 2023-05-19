package com.chocolatecake.marvel.domain.mapper.series

import com.chocolatecake.marvel.data.remote.model.dto.EventDto
import com.chocolatecake.marvel.domain.mapper.Mapper
import com.chocolatecake.marvel.domain.model.Event
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class EventsDtoToUiMapper @Inject constructor() : Mapper<EventDto, Event> {
    override fun map(input: EventDto): Event {
        return Event(
            id = input.id ?: 0,
            imageURL = input.thumbnail?.toUrl() ?: ""
        )
    }
}