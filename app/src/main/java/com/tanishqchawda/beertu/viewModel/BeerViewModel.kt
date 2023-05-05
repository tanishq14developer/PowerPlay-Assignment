package com.tanishqchawda.beertu.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tanishqchawda.beertu.model.BeerResponseModelItem
import com.tanishqchawda.beertu.network.ApiEndPoints
import com.tanishqchawda.beertu.network.ApiUtils
import com.tanishqchawda.beertu.paging.PagingSourceForPaginatedResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


@HiltViewModel
class BeerViewModel @Inject constructor(
    private val application: Application, private val apiInterFace: ApiEndPoints
) : ViewModel() {


    fun getBeer(): Flow<PagingData<BeerResponseModelItem>> {
        return Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2),
            pagingSourceFactory = {
                PagingSourceForPaginatedResponse(
                    apiInterFace
                )
            }
        ).flow.cachedIn(viewModelScope)

    }


}