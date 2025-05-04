package com.example.mangafaceapp.data.model

import com.google.gson.annotations.SerializedName

data class MangaResponse(
    @SerializedName("data")
    val data : List<Manga>,
    val currentPage: Int,
    val totalPages: Int
)
