package com.example.mangafaceapp.data.remote

import com.example.mangafaceapp.data.model.Manga
import com.example.mangafaceapp.data.model.MangaResponse
import com.example.mangafaceapp.presentation.navigation.BottomNavItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import java.security.Key

interface MangaApiService {
    @GET("manga/fetch")
    suspend fun fetchMangaData(
//        @Header("X-RapidAPI-Key") apiKey: String = "59279c62b7mshf42b378350f7acfp1cfb29jsnff4523f7f6b8",
        @Query("page") page: Int = 1
    ): Response<MangaResponse>
}