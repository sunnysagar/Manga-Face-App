package com.example.mangafaceapp.data.model

import android.accessibilityservice.GestureDescription
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mangafaceapp.data.database.Converters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "manga")
data class Manga(
    @PrimaryKey val id: String,
    val title: String,
    val subTitle: String,
    val status: String,
    val thumb: String,
    val summary: String,
    @TypeConverters(Converters::class) val authors: List<String>,
    @TypeConverters(Converters::class) val genres: List<String>,
    val nsfw: Boolean,
    val type: String,
    @SerializedName("total_chapter") val totalChapter: Int=0,
    @SerializedName("create_at") val createdAt: Long = 0,
    @SerializedName("update_at") val updatedAt: Long= 0
)
