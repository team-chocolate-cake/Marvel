package com.chocolatecake.marvel.ui.creator_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class ComicAdapter(comicList: List<ComicDto>, listener: CreatorDetailsListener,) :
    BaseAdapter<ComicDto?>(comicList, listener) {

    override val layoutId = R.layout.comic_item
}