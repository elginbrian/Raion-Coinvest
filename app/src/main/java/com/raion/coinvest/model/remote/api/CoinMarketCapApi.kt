package com.raion.coinvest.model.remote.api

import com.raion.coinvest.R
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface CoinMarketCapApi {
    @GET("/v1/cryptocurrency/listings/latest")
    suspend fun getLatestListing(
        @Header("X-CMC_PRO_API_KEY") apiKey: String = "e50e6ab4-10ce-4bde-8e4c-fd419cbafc2a"
    ): GetLatestListingResponse
}