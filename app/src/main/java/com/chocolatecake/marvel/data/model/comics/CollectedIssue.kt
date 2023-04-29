package com.chocolatecake.marvel.data.model.comics


import com.google.gson.annotations.SerializedName

data class CollectedIssue(
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("resourceURI")
    val resourceURI: String? = null
)