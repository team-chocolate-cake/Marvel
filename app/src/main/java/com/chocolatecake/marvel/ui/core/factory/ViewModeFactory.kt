package com.chocolatecake.marvel.ui.core.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chocolatecake.marvel.ui.comic_details.ComicDetailsViewModel

class ViewModeFactory(private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
         when (modelClass) {
            ComicDetailsViewModel::class.java -> {
                return ComicDetailsViewModel(id) as T
            }


        }
        throw Exception("Error")
    }
}