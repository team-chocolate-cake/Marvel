package com.chocolatecake.marvel.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val description: String,
    val imageURL: String,
    val title: String
)
