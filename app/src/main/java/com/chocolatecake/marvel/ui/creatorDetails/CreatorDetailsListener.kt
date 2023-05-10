package com.chocolatecake.marvel.ui.creatorDetails

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface CreatorDetailsListener:BaseAdapter.BaseInteractionListener {
    fun onClickSeries(seriesId:Int?)
    fun onClickComics(comicsId:Int?)

}