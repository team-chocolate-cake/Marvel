package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("code")
    val code: Int? = null,
    @SerializedName("data")
    val data: Data<T>? = null,
    @SerializedName("status")
    val status: String? = null,
)