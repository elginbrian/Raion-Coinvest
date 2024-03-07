package com.raion.coinvest.data.remote.api.model

import com.google.gson.annotations.SerializedName

data class GetListWithMarketData(
    val cryptoDataList: List<CryptoData2>
)

data class CryptoData2(
    val id: String,
    val name: String,
    @SerializedName("market_cap")
    val marketCap: Double?,
    @SerializedName("market_cap_change_24h")
    val marketCapChange24h: Double?,
    val content: String,
    @SerializedName("top_3_coins")
    val top3Coins: List<String>,
    @SerializedName("volume_24h")
    val volume24h: Double?,
    @SerializedName("updated_at")
    val updatedAt: String
)