package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.series_details.view.SeriesDetailsListener

class ComicsAdapter(itemsComics: List<ComicsResult?>, listener: SeriesDetailsListener) :
    BaseAdapter<ComicsResult?>(itemsComics, listener){

    override val layoutId: Int = R.layout.series_details_comics_item
}