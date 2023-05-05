package com.tanishqchawda.beertu.model

data class GenericPaginatedResponse<out T>(
    val data:List<T>
)