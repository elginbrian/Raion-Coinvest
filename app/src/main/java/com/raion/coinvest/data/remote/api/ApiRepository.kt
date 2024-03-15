package com.raion.coinvest.data.remote.api

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.api.model.Data
import com.raion.coinvest.data.remote.api.model.GetListWithMarketData
import com.raion.coinvest.data.remote.api.model.GetTrendingSearchList
import com.raion.coinvest.data.remote.api.model.GetTrendingStocks
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val coinGeckoApi: CoinGeckoApi,
    private val goApi: GoApi
){
    val db = Firebase.firestore
    private suspend fun getCoinGeckoApiKey(): String{
        var apiKey: String = ""
        db.collection("CoinGeckoApiKey").get().addOnSuccessListener{
            apiKey = it.documents[0].data?.keys.toString()
        }.await()
        return apiKey
    }
    private suspend fun getGoApiKey(): String{
        var apiKey: String = ""
        db.collection("GoApiKey").get().addOnSuccessListener {result ->
            for(document in result){
                apiKey = document["key"].toString()
            }
            Log.d("apiKey", apiKey)
        }.await()
        return apiKey
    }

    suspend fun getListWithMarketData(): List<GetListWithMarketData> {
        return coinGeckoApi.getListWithMarketData(getCoinGeckoApiKey())
    }

    suspend fun getTrendingSearchList(): GetTrendingSearchList {
        return coinGeckoApi.getTrendingSearchList(getCoinGeckoApiKey())
    }

    suspend fun getTrendingStocks(): GetTrendingStocks {
        var result = GetTrendingStocks("","", Data(listOf()))
        try {
            result =  goApi.getTrendingStocks(getGoApiKey())
        } catch (e: Exception) {

        }
        return result
    }
}