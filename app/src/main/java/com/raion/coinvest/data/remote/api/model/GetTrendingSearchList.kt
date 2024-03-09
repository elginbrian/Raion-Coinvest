package com.raion.coinvest.data.remote.api.model

import com.google.gson.annotations.SerializedName

data class GetTrendingSearchList(
    val coins: List<CoinItem>
)

data class CoinItem(
    val item: CoinDetails
)

data class CoinDetails(
    val id: String,
    @SerializedName("coin_id")
    val coinId: Int,
    val name: String,
    val symbol: String,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int,
    val thumb: String,
    val small: String,
    val large: String,
    val slug: String,
    @SerializedName("price_btc")
    val priceBTC: Double,
    val score: Int,
    val data: CoinData
)

data class CoinData(
    val price: String,
    @SerializedName("price_btc")
    val priceBTC: String,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Map<String, Double>,
    @SerializedName("market_cap")
    val marketCap: String,
    @SerializedName("market_cap_btc")
    val marketCapBTC: String,
    @SerializedName("total_volume")
    val totalVolume: String,
    @SerializedName("total_volume_btc")
    val totalVolumeBTC: String,
    val sparkline: String,
    val content: CoinContent?
)

data class CoinContent(
    val title: String,
    val description: String
)
