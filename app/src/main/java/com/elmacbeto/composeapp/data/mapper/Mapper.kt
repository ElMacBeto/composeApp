package com.elmacbeto.composeapp.data.mapper

interface Mapper<I, O> {
    suspend fun map(input: I): O
}