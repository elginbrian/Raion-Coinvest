package com.raion.coinvest.data.remote.api.model

data class GetTrendingStocks(
    val status: String,
    val message: String,
    val data: Data
)
data class Data(
    val results: List<Stock>
)
data class Company(
    val symbol: String,
    val name: String,
    val logo: String
)
data class Stock(
    val symbol: String,
    val company: Company,
    val close: Int,
    val change: Int,
    val percent: Double
)


