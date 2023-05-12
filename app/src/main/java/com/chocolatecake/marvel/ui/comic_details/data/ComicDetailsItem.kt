package com.chocolatecake.marvel.ui.comic_details.data

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.EventResult
import com.chocolatecake.marvel.data.model.ProfileResult

sealed class ComicDetailsItem(val sortCondition: Int) {
    data class Header(val comic: ComicsResult) : ComicDetailsItem(FIRST)
    data class Characters(val list: List<ProfileResult>) :
        ComicDetailsItem(SECOND)

    data class Events(val eventResult: EventResult) : ComicDetailsItem(THIRD)

    companion object {
        private const val FIRST = 1
        private const val SECOND = 2
        private const val THIRD = 3
    }
}


