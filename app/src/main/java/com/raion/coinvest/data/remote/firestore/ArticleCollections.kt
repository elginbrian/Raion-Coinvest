package com.raion.coinvest.data.remote.firestore

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.net.Uri
import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.raion.coinvest.data.remote.firebaseStorage.ImageRepository
import com.raion.coinvest.data.remote.firestore.model.ArticleDataClass
import com.raion.coinvest.data.remote.firestore.model.UserDataClass
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class ArticleCollections @Inject constructor(
    private val imageRepository: ImageRepository
) {
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

    suspend fun getArticle(): MutableList<ArticleDataClass>{
        val articleList: MutableList<ArticleDataClass> = mutableListOf()
        db.collection("articles")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")

                    val articleAuthorData = document.get("articleAuthor") as? Map<*, *>
                    val articleAuthor  = UserDataClass(
                        userId         = articleAuthorData?.get("userId").toString(),
                        userName       = articleAuthorData?.get("userName").toString(),
                        profilePicture = articleAuthorData?.get("profilePicture").toString(),
                        accountType    = articleAuthorData?.get("accountType").toString(),
                        email          = articleAuthorData?.get("email").toString()
                    )

                    articleList.add(element = ArticleDataClass(
                        articleId        = document["articleId"].toString(),
                        articleTitle     = document["articleTitle"].toString(),
                        articleContent   = document["articleContent"].toString(),
                        articleAuthor    = articleAuthor,
                        articleCreatedAt = document["articleCreatedAt"].toString(),
                        imageUri         = Uri.EMPTY
                    )
                    )
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "Error getting documents: ", exception)
            }.await()
        return articleList
    }
}