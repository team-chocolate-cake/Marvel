package com.chocolatecake.marvel.ui.core.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chocolatecake.marvel.ui.character_details.CharacterDetailsViewModel
import com.chocolatecake.marvel.ui.comic_details.ComicDetailsViewModel
import com.chocolatecake.marvel.ui.creatorDetails.CreatorDetailsViewModel
import com.chocolatecake.marvel.ui.event_details.view_model.EventDetailsViewModel
import com.chocolatecake.marvel.ui.series_details.viewModel.SeriesDetailsViewModel
import com.chocolatecake.marvel.ui.storiesDetails.view_model.StoriesDetailsViewModel

class ViewModeFactory(private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when (modelClass) {
            ComicDetailsViewModel::class.java -> {
                return ComicDetailsViewModel(id) as T
            }

            CharacterDetailsViewModel::class.java -> {
                 return CharacterDetailsViewModel(id) as T
            }

            CreatorDetailsViewModel::class.java -> {
                 return CreatorDetailsViewModel(id) as T
            }

            EventDetailsViewModel::class.java -> {
                return EventDetailsViewModel(id) as T
            }

            StoriesDetailsViewModel::class.java -> {
                return StoriesDetailsViewModel(id) as T
            }

            SeriesDetailsViewModel::class.java -> {
                return  SeriesDetailsViewModel(id) as T
            }
        }
        throw Exception("Error")
    }
}