package com.chocolatecake.marvel.ui.home.view

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface HomeListener: BaseAdapter.BaseInteractionListener {
    fun onClickBanner(eventId: Int?)
    fun onClickSeries(seriesId: Int?)

    fun onClickMoreSeries()

}