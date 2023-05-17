package com.chocolatecake.marvel.data.remote.model.dto

import com.chocolatecake.marvel.data.remote.model.ImageResponse
import com.google.gson.annotations.SerializedName

data class SeriesDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("endYear")
    val endYear: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("startYear")
    val startYear: Int? = null,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse? = null,
    @SerializedName("title")
    val title: String? = null,
)