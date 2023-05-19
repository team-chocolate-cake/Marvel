package com.chocolatecake.marvel.ui.comic

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.ComicListener

class ComicsAdapter(
    items: List<Comic>,
    listener: ComicListener,
) : BaseAdapter<Comic>(items, listener) {

    override val layoutId = R.layout.item_comic
}

