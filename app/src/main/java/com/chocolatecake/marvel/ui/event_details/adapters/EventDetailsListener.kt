package com.chocolatecake.marvel.ui.event_details.adapters

import com.chocolatecake.marvel.ui.core.listener.CharacterListener
import com.chocolatecake.marvel.ui.core.listener.ComicListener
import com.chocolatecake.marvel.ui.core.listener.SeriesListener

interface EventDetailsListener : ComicListener, CharacterListener, SeriesListener