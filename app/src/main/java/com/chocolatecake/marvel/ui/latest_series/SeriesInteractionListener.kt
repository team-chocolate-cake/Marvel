package com.chocolatecake.marvel.ui.latest_series

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface SeriesInteractionListener:BaseAdapter.BaseInteractionListener {
    fun onClickSeries(seriesId: Int)
}