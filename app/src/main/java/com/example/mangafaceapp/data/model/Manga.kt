package com.example.mangafaceapp.data.model

import android.accessibilityservice.GestureDescription
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mangafaceapp.data.database.Converters

@Entity(tableName = "manga")
data class Manga(
    @PrimaryKey val id: Int,
    val title: String,
    val subTitle: String,
    val status: String,
    val thumb: String,
    val summary: String,
    @TypeConverters(Converters::class) val authors: List<String>,
    @TypeConverters(Converters::class) val genres: List<String>,
    val nsfw: Boolean,
    val type: String,
    val totalChapter: Int,
    val createdAt: Long,
    val updatedAt: Long
)
