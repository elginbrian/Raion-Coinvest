package com.raion.coinvest.data.remote.api

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.api.model.GetListWithMarketData
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val coinGeckoApi: CoinGeckoApi
){
    val db = Firebase.firestore
    private suspend fun getApiKey(): String{
        var apiKey: String = ""
        db.collection("CoinGeckoApiKey").get().addOnSuccessListener{
            apiKey = it.documents[0].data?.keys.toString()
        }.await()
        return apiKey
    }

    suspend fun getListWithMarketData(): List<GetListWithMarketData> {
        return coinGeckoApi.getListWithMarketData(getApiKey())
    }

    suspend fun getTrendingSearchList(): GetTrendingSearchList {
        return coinGeckoApi.getTrendingSearchList(getApiKey())
    }
}