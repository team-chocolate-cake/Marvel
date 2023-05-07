package com.chocolatecake.marvel.ui.event_details.view

import com.chocolatecake.marvel.ui.base.BaseAdapter.BaseInteractionListener

interface EventDetailsListener : BaseInteractionListener {
    fun onClickComics(comicsId: Int)
    fun onClickCharacter(characterId: Int)
    fun onClickSeries(seriesId: Int)
}