package com.example.newsbreeze.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(
    tableName = "articles",
    indices = [Index(
        value = [ "content", "description", ],
        unique = true
    )]
)
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: Source?,
    val title: String?,
    val url: String?,
    val urlToImage: String?
) : Parcelable