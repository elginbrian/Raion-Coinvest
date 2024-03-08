package com.raion.coinvest.data.remote.api

import com.raion.coinvest.data.remote.api.model.GetListWithMarketData
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import io.grpc.android.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Header
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface CoinGeckoApi {
    @GET("coins/categories")
    suspend fun getListWithMarketData(
        @Header("x_cg_demo_api_key") apiKey: String
    ): List<GetListWithMarketData>

    @GET("search/trending")
    suspend fun getTrendingSearchList(
        @Header("x_cg_demo_api_key") apiKey: String
    ): GetTrendingSearchList
}