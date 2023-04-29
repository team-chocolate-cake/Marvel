package com.chocolatecake.marvel.data.remote.service

import com.chocolatecake.marvel.data.model.base.BaseResponse
import com.chocolatecake.marvel.data.model.comics.ComicsResult
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MarvelService {
    @GET("comics")
    fun getComics(): Single<Response<BaseResponse<ComicsResult>>>

    @GET("comics/{id}")
    fun getComicsById(
        @Path("id") id: Int ,
    ): Single<Response<BaseResponse<ComicsResult>>>

}