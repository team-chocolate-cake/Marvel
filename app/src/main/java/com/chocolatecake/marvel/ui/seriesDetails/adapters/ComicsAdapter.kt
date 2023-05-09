package com.chocolatecake.marvel.ui.seriesDetails.adapters

import com.chocolatecake.marvel.R
import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.ui.base.BaseAdapter
import com.chocolatecake.marvel.ui.seriesDetails.view.SeriesDetailsListener

class ComicsAdapter(itemsComics: List<ComicsResult?>, listener: SeriesDetailsListener)
    : BaseAdapter<ComicsResult?>(itemsComics, listener){

    override val layoutId: Int = R.layout.comics_item
}