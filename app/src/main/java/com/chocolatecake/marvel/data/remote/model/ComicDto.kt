package com.chocolatecake.marvel.data.remote.model

import com.google.gson.annotations.SerializedName

data class ComicDto(
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("images")
    val images: List<ImageResponse>? = listOf(),
    @SerializedName("pageCount")
    val pageCount: Int? = null,
    @SerializedName("textObjects")
    val textObjects: List<TextObject>? = listOf(),
    @SerializedName("thumbnail")
    val thumbnail: ImageResponse?,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("variants")
    val variants: List<ItemResponse>? = listOf(),
) {
    data class ItemResponse(
        @SerializedName("name")
        val name: String? = null,
    )

    data class TextObject(
        @SerializedName("text")
        val text: String? = null,
    )

    fun getTextObject(): String? {
        return if (textObjects?.isNotEmpty() == true) {
            textObjects[0].text
        } else {
            null
        }
    }
}