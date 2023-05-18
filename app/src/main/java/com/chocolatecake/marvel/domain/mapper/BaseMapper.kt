package com.chocolatecake.marvel.domain.mapper

abstract class BaseMapper<I,O> : Mapper<I, O> {
    abstract override fun map(input: I): O
}