package com.chocolatecake.marvel.ui.character_details

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult

sealed class CharacterDetailsItem {
    data class Header(val characterInfo: ProfileResult?) : CharacterDetailsItem()

    data class Comics(val result: List<ComicsResult?>) : CharacterDetailsItem()
}