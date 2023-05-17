package com.chocolatecake.marvel.ui.comic

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.ComicListener

class ComicsAdapter(
    items: List<ComicDto>,
    listener: ComicListener,
) : BaseAdapter<ComicDto>(items, listener) {

    override val layoutId = R.layout.item_comic
}

