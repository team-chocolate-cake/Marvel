package com.chocolatecake.marvel.data.local.entities

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
