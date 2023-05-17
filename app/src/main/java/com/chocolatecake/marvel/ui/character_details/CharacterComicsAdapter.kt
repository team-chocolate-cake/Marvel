package com.chocolatecake.marvel.ui.character_details

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.ComicListener

class CharacterComicsAdapter(
    list: List<ComicDto>, listener: ComicListener,
) : BaseAdapter<ComicDto>(list, listener) {

    override val layoutId: Int = R.layout.comic_item
}