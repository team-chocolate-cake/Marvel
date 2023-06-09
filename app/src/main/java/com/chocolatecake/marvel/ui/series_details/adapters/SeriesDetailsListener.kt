package com.chocolatecake.marvel.ui.series_details.adapters

import com.chocolatecake.marvel.ui.core.listener.CharacterListener
import com.chocolatecake.marvel.ui.core.listener.ComicListener
import com.chocolatecake.marvel.ui.core.listener.EventListener

interface SeriesDetailsListener : EventListener, ComicListener, CharacterListener
