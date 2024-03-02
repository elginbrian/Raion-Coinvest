package com.raion.coinvest.data.remote.api.model

data class GetLatestListingResponse(
    val data: List<CryptoData>,
    val status: Status
)
data class CryptoData(
    val id: Int,
    val name: String,
    val symbol: String,
    val slug: String,
    val cmcRank: Int,
    val numMarketPairs: Int,
    val circulatingSupply: Long,
    val totalSupply: Long,
    val maxSupply: Long,
    val lastUpdated: String,
    val dateAdded: String,
    val tags: List<String>,
    val platform: Any?, // This is null in the provided data
    val quote: Quote
)

data class Quote(
    val USD: PriceInfo,
    val BTC: PriceInfo
)

data class PriceInfo(
    val price: Double,
    val volume24h: Long,
    val percentChange1h: Double,
    val percentChange24h: Double,
    val percentChange7d: Double,
    val marketCap: Long,
    val lastUpdated: String
)

data class Status(
    val timestamp: String,
    val errorCode: Int,
    val errorMessage: String,
    val elapsed: Int,
    val creditCount: Int
)
