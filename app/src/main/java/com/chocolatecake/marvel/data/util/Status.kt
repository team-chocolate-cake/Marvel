package com.chocolatecake.marvel.data.util

sealed class State<out T> {
    object Loading : State<Nothing>()
    data class Failure(val message: String) : State<Nothing>()
    data class Success<T>(val data: T) : State<T>()

    fun toData(): T? = if (this is Success) data else null
}