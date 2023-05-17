package com.chocolatecake.marvel.ui.comic_details

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.ProfileDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class CharactersAdapter(
    items: List<ProfileDto>,
    listener: ComicInteractionListener,
) : BaseAdapter<ProfileDto>(items,listener) {

    override val layoutId: Int = R.layout.character_item

}