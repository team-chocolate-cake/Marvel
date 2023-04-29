package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.comics.ComicsResult
import com.chocolatecake.marvel.data.util.State
import io.reactivex.rxjava3.core.Single

interface MarvelRepository {
    fun getComics(): Single<State<BaseResponse<ComicsResult>?>>

    fun getComicsById(comicId: Int): Single<State<BaseResponse<ComicsResult>?>>

    fun getComicsCharacters(comicId: Int): Single<State<BaseResponse<ComicsResult>?>>
}