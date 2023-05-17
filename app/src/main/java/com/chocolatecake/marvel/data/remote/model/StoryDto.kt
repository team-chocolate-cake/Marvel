package com.chocolatecake.marvel.data.remote.model

import com.google.gson.annotations.SerializedName

data class StoryDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("type")
    val type: String? = null,
    @SerializedName("modified")
    val date: String? = null,
){
    fun getFormattedDate(): String?{
        return date?.split("T")?.first()
    }
}