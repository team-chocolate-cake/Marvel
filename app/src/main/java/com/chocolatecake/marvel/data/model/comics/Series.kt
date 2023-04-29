package com.chocolatecake.marvel.data.model.comics


import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)