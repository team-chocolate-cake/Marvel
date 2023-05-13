package com.chocolatecake.marvel.ui.home.adapter

import com.chocolatecake.marvel.ui.core.listener.ComicListener
import com.chocolatecake.marvel.ui.core.listener.EventListener
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

interface HomeListener: EventListener, SeriesListener, ComicListener {
    fun onClickMoreComics()
    fun onClickMoreSeries()
}