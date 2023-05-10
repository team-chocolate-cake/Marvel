package com.chocolatecake.marvel.data.model.base

import com.chocolatecake.marvel.data.model.ComicsResult
import com.chocolatecake.marvel.data.model.ProfileResult
import com.chocolatecake.marvel.data.model.SeriesResult

data class SearchResult(
    val profileResults: List<ProfileResult>,
    val seriesResults: List<SeriesResult>,
    val comicsResult: List<ComicsResult>
)

/*
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
) : SearchResult()

data class ComicsResult(
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
) : SearchResult()
*/
