package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firestore.model.NewsDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await

class NewsCollections {
    val db = Firebase.firestore

    suspend fun addNews(news: NewsDataClass){
        try {
            val newsHashMap = hashMapOf(
                "newsId" to news.newsId,
                "newsTitle" to news.newsTitle,
                "newsCategory" to news.newsCategory,
                "newsAuthor" to news.newsAuthor,
                "newsCreatedAt" to news.newsCreatedAt,
                "newsContent" to news.newsContent,
            )

            db.collection("news").document(news.newsId).set(newsHashMap).addOnSuccessListener { documentReference ->
                Log.d(ContentValues.TAG, "DocumentSnapshot written with ID: $documentReference")

            }.addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding document", e)
            }.await()
        } catch (_: Exception){

        }
    }

    suspend fun getNews(): MutableList<NewsDataClass>{
        val newsList: MutableList<NewsDataClass> = mutableListOf()
        db.collection("news").get().addOnCompleteListener{ result ->
            for(document in result.result){
                Log.d(ContentValues.TAG, "${document.id} => ${document.data}")

                val newsAuthorData = document.get("newsAuthor") as? Map<*, *>
                val newsAuthor     = UserDataClass(
                    userId         = newsAuthorData?.get("userId").toString(),
                    userName       = newsAuthorData?.get("userName").toString(),
                    profilePicture = newsAuthorData?.get("profilePicture").toString(),
                    accountType    = newsAuthorData?.get("accountType").toString(),
                    email          = newsAuthorData?.get("email").toString()
                )

                newsList.add( element = NewsDataClass(
                    newsId        = document["newsId"].toString(),
                    newsTitle     = document["newsTitle"].toString(),
                    newsCategory  = document["newsCategory"].toString(),
                    newsAuthor    = newsAuthor,
                    newsContent   = document["newsContent"].toString(),
                    newsCreatedAt = document["newsCreatedAt"].toString(),
                    imageUri      = Uri.EMPTY,
                )
                )
            }
        }.addOnFailureListener { exception ->
            Log.d(ContentValues.TAG, "Error getting documents: ", exception)
        }.await()
        return newsList
    }
}