package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await

class ArticleCollections {
    val db = Firebase.firestore
    suspend fun addArticle(article: ArticleDataClass){
        try {
            val articleHashMap = hashMapOf(
                "articleId" to article.articleId,
                "articleTitle" to article.articleTitle,
                "articleAuthor" to article.articleAuthor,
                "articleCreatedAt" to article.articleCreatedAt,
                "articleContent" to article.articleContent
            )

            db.collection("articles").document(article.articleId).set(articleHashMap).addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: $documentReference")

            }.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }.await()
        } catch (e: Exception){

        }
    }
}