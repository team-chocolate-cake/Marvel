package com.chocolatecake.marvel.ui.stories_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class ComicsAdapter (comicList: List<ComicDto>, listener: SeriesListener) :
    BaseAdapter<ComicDto?>(comicList, listener) {

    override val layoutId = R.layout.comic_item
}