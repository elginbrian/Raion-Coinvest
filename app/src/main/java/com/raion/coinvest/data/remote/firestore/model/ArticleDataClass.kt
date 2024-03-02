package com.raion.coinvest.data.remote.firestore.model

data class ArticleDataClass(
    val articleId: String,
    val articleTitle: String,
    val articleAuthor: UserDataClass,
    val articleCreatedAt: String,
    val articleContent: String
)