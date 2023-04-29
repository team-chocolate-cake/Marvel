package com.chocolatecake.marvel.data.model.comics

import com.google.gson.annotations.SerializedName

data class Characters(
    @SerializedName("available")
    val available: Int? = null,
    @SerializedName("collectionURI")
    val collectionURI: String? = null,
    @SerializedName("items")
    val items: List<ItemsCharacters?>? = null,
    @SerializedName("returned")
    val returned: Int? = null
)