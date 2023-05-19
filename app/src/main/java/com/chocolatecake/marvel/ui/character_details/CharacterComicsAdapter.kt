package com.chocolatecake.marvel.ui.character_details

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.ComicListener

class CharacterComicsAdapter(
    list: List<Comic>, listener: ComicListener,
) : BaseAdapter<Comic>(list, listener) {

    override val layoutId: Int = R.layout.comic_item
}