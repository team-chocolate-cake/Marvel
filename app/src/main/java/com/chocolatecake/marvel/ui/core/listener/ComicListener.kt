package com.chocolatecake.marvel.ui.core.listener

import com.chocolatecake.marvel.ui.base.BaseAdapter

interface ComicListener : BaseAdapter.BaseInteractionListener {
    fun onClickComic(id: Int)
}