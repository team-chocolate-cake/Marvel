package com.chocolatecake.marvel.ui.comic_details

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface ComicInteractionListener : BaseAdapter.BaseInteractionListener {
    fun onCharacterClick(characterId: Int?)
    fun onEventClick(eventId: Int?)
}