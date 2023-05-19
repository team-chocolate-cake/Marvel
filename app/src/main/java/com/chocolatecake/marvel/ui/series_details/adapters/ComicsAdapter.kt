package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.remote.model.dto.ComicDto
import com.chocolatecake.marvel.domain.model.Comic
import com.chocolatecake.marvel.ui.base.BaseAdapter

class ComicsAdapter(itemsComics: List<Comic?>, listener: SeriesDetailsListener) :
    BaseAdapter<Comic?>(itemsComics, listener){

    override val layoutId: Int = R.layout.series_details_comics_item
}