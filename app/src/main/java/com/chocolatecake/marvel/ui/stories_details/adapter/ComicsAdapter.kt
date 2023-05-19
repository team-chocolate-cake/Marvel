package com.chocolatecake.marvel.ui.stories_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class ComicsAdapter (comicList: List<Comic>, listener: SeriesListener) :
    BaseAdapter<Comic>(comicList, listener) {

    override val layoutId = R.layout.comic_item
}