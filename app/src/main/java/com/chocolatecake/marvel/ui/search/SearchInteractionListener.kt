package com.chocolatecake.marvel.ui.search

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface SearchInteractionListener : BaseAdapter.BaseInteractionListener {
    fun onclick(id:Int?)
}