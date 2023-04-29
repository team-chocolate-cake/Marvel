package com.chocolatecake.marvel.data.model

import com.google.gson.annotations.SerializedName

data class ItemResponse(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
)
