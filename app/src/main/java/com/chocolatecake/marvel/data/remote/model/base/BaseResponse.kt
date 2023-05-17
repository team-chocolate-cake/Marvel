package com.chocolatecake.marvel.data.remote.model.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val data: Data<T>? = null,
    @SerializedName("status")
    val status: String? = null,
){
    data class Data<T>(
        @SerializedName("count")
        val count: Int? = null,
        @SerializedName("results")
        val results: List<T?>? = listOf(),
    )
}