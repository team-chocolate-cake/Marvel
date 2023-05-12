package com.chocolatecake.marvel.ui.storiesDetails.adapter

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

class ComicsAdapter (comicList: List<ComicsResult>, listener: SeriesListener) :
    BaseAdapter<ComicsResult?>(comicList, listener) {

    override val layoutId = R.layout.comic_item
}