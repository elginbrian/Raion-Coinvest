package com.raion.coinvest.data.remote.api

import com.raion.coinvest.data.remote.api.model.GetTrendingStocks
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface GoApi {
    @GET("stock/idx/trending")
    suspend fun getTrendingStocks(@Header("X-API-KEY") apiKey: String): GetTrendingStocks
}