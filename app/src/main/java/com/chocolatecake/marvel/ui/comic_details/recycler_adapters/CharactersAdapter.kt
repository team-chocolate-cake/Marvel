package com.chocolatecake.marvel.ui.comic_details.recycler_adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.comic_details.ComicInteractionListener

class CharactersAdapter(
    items: List<ProfileResult>,
    listener: ComicInteractionListener
) : BaseAdapter<ProfileResult>(items,listener) {

    override val layoutId: Int = R.layout.character_item

}