package com.chocolatecake.marvel

import com.chocolatecake.marvel.data.repository.MarvelRepository
import com.chocolatecake.marvel.data.repository.MarvelRepositoryImpl
import com.chocolatecake.marvel.ui.base.BaseViewModel

class CharacterDetailsViewModel : BaseViewModel() {
    private val repository: MarvelRepository by lazy { MarvelRepositoryImpl() }
}