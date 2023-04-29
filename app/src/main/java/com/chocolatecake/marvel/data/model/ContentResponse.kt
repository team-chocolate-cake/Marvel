package com.chocolatecake.marvel.data.model

import com.google.gson.annotations.SerializedName

data class ContentResponse<T>(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<T?>? = null,
    @SerializedName("returned")
    val returned: Int? = null,
)
