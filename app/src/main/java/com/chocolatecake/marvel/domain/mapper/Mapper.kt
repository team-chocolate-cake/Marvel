package com.chocolatecake.marvel.domain.mapper

interface Mapper <INPUT, OUTPUT> {

    fun map (input: INPUT):OUTPUT
}