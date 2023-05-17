package com.chocolatecake.marvel.data.remote.model.dto

import com.chocolatecake.marvel.data.remote.model.ImageResponse
import com.google.gson.annotations.SerializedName

data class ProfileDto(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("fullName")
    val fullName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
)