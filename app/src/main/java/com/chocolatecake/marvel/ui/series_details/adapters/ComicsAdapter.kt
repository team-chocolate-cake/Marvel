package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.ComicDto
import com.chocolatecake.marvel.ui.base.BaseAdapter

class ComicsAdapter(itemsComics: List<ComicDto?>, listener: SeriesDetailsListener) :
    BaseAdapter<ComicDto?>(itemsComics, listener){

    override val layoutId: Int = R.layout.series_details_comics_item
}