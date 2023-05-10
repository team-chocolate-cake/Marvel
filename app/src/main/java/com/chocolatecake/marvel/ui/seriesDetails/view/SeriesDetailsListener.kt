package com.chocolatecake.marvel.ui.seriesDetails.view

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface SeriesDetailsListener: BaseAdapter.BaseInteractionListener {
        fun onClickEvent(eventId: Int?)
        fun onClickComic(comicId: Int?)
        fun onClickCharacter(characterId: Int?)
}