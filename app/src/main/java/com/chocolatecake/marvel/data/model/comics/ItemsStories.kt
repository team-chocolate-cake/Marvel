package com.chocolatecake.marvel.data.model.comics


import com.google.gson.annotations.SerializedName

data class ItemsStories(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null,
    @SerializedName("type")
    val type: String? = null
)