package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class Data<T>(
    @SerializedName("count")
    val count: Int? = null,
    @SerializedName("limit")
    val limit: Int? = null,
    @SerializedName("offset")
    val offset: Int? = null,
    @SerializedName("results")
    val results: List<T?>? = listOf(),
    @SerializedName("total")
    val total: Int? = null,
)