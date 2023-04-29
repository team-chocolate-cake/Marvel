package com.chocolatecake.marvel.data.model.base

import com.google.gson.annotations.SerializedName

data class BaseContent<T>(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<T?>? = null,
    @SerializedName("returned")
    val returned: Int? = null,
)
