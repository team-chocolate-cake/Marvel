package com.chocolatecake.marvel.ui.character_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.comic.ComicListener

class CharacterComicsAdapter(
    list: List<ComicsResult>, listener: ComicListener,
) : BaseAdapter<ComicsResult>(list, listener) {
    override val layoutId: Int = R.layout.item_comic
}