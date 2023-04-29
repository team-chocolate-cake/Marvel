package com.chocolatecake.marvel.data.repository

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.comics.ComicsResult
import com.chocolatecake.marvel.data.remote.service.MarvelService
import com.chocolatecake.marvel.data.util.State
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class MarvelRepositoryImpl(private val apiService: MarvelService) : MarvelRepository{
    override fun getComics(): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComics())
    }

    override fun getComicsById(comicId: Int): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComicsById(comicId))
    }

    override fun getComicsCharacters(comicId: Int): Single<State<BaseResponse<ComicsResult>?>> {
        return wrapperToState(apiService.getComicsCharacters(comicId))
    }


    private fun <T> wrapperToState(response: Single<Response<BaseResponse<T>>>)
    :Single<State<BaseResponse<T>?>> {
        return response.map {
            try {
                if (it.isSuccessful) {
                    State.Success(it.body())
                } else {
                    State.Failure(it.message())
                }
            }catch (e: Exception){
                State.Failure(e.message.toString())
            }
        }
    }
}