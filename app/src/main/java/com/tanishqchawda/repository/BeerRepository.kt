package com.tanishqchawda.repository

import android.content.Context
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.tanishqchawda.beertu.network.ApiEndPoints
import com.tanishqchawda.beertu.network.ApiUtils
import com.tanishqchawda.beertu.paging.PagingSourceForPaginatedResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class BeerRepository @Inject constructor(
    private val apiInterFace: ApiEndPoints,
    private val apiUtils: ApiUtils,
    @ApplicationContext private val context: Context
) {
    fun getBeer() = Pager(
        config = PagingConfig(10, 1),
        pagingSourceFactory = {
            PagingSourceForPaginatedResponse(apiInterFace)
        }
    ).flow

}