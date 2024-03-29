package com.raion.coinvest.presentation.screen.stocksSection

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raion.coinvest.data.remote.api.ApiRepository
import com.raion.coinvest.data.remote.api.model.GetListWithMarketData
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.data.remote.api.model.GetTrendingStocks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val apiRepository: ApiRepository
): ViewModel() {

    private var isFetching = false
    private var isFetching2 = false
    fun getListWithMarketData(
        onFinished: (List<GetListWithMarketData>) -> Unit
    ){
        if(!isFetching){
            isFetching = true
            viewModelScope.launch {
                val result = apiRepository.getListWithMarketData()
                isFetching = false
                onFinished(result)
            }
        }
    }

    fun getTrendingSearchList(
        onFinished: (GetTrendingSearchList) -> Unit
    ){
        if(!isFetching){
            isFetching = true
            viewModelScope.launch {
                val result = apiRepository.getTrendingSearchList()
                isFetching = false
                onFinished(result)
            }
        }
    }

    fun getTrendingStocks(
        onFinished: (GetTrendingStocks) -> Unit
    ){
        if(!isFetching2){
            isFetching2 = true
            viewModelScope.launch {
                val result = apiRepository.getTrendingStocks()
                isFetching2 = false
                onFinished(result)
            }
        }
    }
}