package com.example.mangafaceapp.data.model

import android.accessibilityservice.GestureDescription
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.mangafaceapp.data.database.Converters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "manga")
data class Manga(
    @PrimaryKey val id: String,  // No default value for `id`, as it is required.

    val title: String? = "",  // Default value for title is an empty string.

    val sub_title: String? = "",  // Default value for subtitle is an empty string.

    val status: String? = "",  // Default value for status is an empty string.

    val thumb: String? = "",  // Default value for thumbnail URL is an empty string.

    val summary: String? = "",  // Default value for summary is an empty string.

    @TypeConverters(Converters::class) val authors: List<String> = emptyList(),  // Default value for authors list is an empty list.

    @TypeConverters(Converters::class) val genres: List<String> = emptyList(),  // Default value for genres list is an empty list.

    val nsfw: Boolean = false,  // Default value for `nsfw` is false.

    val type: String? = "",  // Default value for type is an empty string.

    @SerializedName("total_chapter") val totalChapter: Int = 0,  // Default value for `totalChapter` is 0.

    @SerializedName("create_at") val createdAt: Long = 0,  // Default value for `createdAt` is 0 (could represent the Unix timestamp).

    @SerializedName("update_at") val updatedAt: Long = 0  // Default value for `updatedAt` is 0 (could represent the Unix timestamp).
)
