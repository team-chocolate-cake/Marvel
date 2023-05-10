package com.chocolatecake.marvel.ui.core.listener

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface SeriesListener : BaseAdapter.BaseInteractionListener {
    fun onClickSeries(id: Int)
}