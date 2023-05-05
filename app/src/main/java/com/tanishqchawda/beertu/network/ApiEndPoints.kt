package com.tanishqchawda.beertu.network

import com.tanishqchawda.beertu.model.BeerResponseModelItem
import com.tanishqchawda.beertu.model.GenericPaginatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiEndPoints
{

    @GET("v2/beers")
    suspend fun getBeers(
        @Query("page") page: Int,
        @Query("per_page") per_page : Int = 10
    ):Response<List<BeerResponseModelItem>>


}