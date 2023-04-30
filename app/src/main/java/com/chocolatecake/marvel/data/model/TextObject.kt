package com.chocolatecake.marvel.data.model

import com.google.gson.annotations.SerializedName

data class TextObject(
    @SerializedName("text")
    val text: String? = null,
)