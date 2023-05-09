package com.chocolatecake.marvel.ui.character_details

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface CharacterDetailsListener : BaseAdapter.BaseInteractionListener {
    fun onClickComic(comicId: Int)
}