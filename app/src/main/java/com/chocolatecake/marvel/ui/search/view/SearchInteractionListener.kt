package com.chocolatecake.marvel.ui.search.view

import com.chocolatecake.marvel.ui.core.listener.CharacterListener
import com.chocolatecake.marvel.ui.core.listener.ComicListener
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

interface SearchInteractionListener : SeriesListener, ComicListener, CharacterListener