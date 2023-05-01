package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class Data<T>(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("results")
    val results: List<T?>? = listOf(),
)