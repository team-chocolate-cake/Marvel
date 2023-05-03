package com.chocolatecake.marvel.ui.event_details

import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.ui.base.BaseViewModel

class EventDetailsViewModel :BaseViewModel() {
    val repository : MarvelRepository by lazy { MarvelRepositoryImpl() }

    private fun getCharactersByEventId(){

    }

    private fun getSeriesByEventId(){

    }

    private fun getComicsByEventId(){

    }

    private fun getEventDetails(){

    }

}