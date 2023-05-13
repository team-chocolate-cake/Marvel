package com.chocolatecake.marvel.ui.creator_details.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter

class ComicAdapter(comicList: List<ComicsResult>, listener: CreatorDetailsListener,) :
    BaseAdapter<ComicsResult?>(comicList, listener) {

    override val layoutId = R.layout.comic_item
}