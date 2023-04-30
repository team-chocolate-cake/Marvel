package com.chocolatecake.marvel.data.model

import com.chocolatecake.marvel.data.model.*
import com.chocolatecake.marvel.data.model.base.*
import com.google.gson.annotations.SerializedName

data class SeriesResult(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("endYear")
    val endYear: Int? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("startYear")
    val startYear: Int? = null,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
    @SerializedName("title")
    val title: String? = null,
)