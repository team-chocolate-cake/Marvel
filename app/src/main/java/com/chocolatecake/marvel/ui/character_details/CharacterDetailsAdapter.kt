package com.chocolatecake.marvel.ui.character_details

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class CharacterDetailsAdapter(
    items: List<ComicsResult>,
    listener: ComicListener,
) : BaseAdapter<ComicsResult>(items, listener) {
    override val layoutId: Int
        get() = R.layout.item_marvel
}

interface ComicListener : BaseAdapter.BaseInteractionListener {
    fun onClickComic(comicId: Int)
}