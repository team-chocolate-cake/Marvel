package com.chocolatecake.marvel.ui.character_details

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.ComicListener

class CharacterComicsAdapter(
    list: List<ComicsResult>, listener: ComicListener,
) : BaseAdapter<ComicsResult>(list, listener) {
    override val layoutId: Int = R.layout.comic_item
}