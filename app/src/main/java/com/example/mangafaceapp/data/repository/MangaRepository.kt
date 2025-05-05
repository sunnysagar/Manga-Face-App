package com.example.mangafaceapp.data.repository

import android.util.Log
import com.example.mangafaceapp.data.dao.MangaDao
import com.example.mangafaceapp.data.model.Manga
import com.example.mangafaceapp.data.remote.MangaApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MangaRepository @Inject constructor(
    private val mangaApiService: MangaApiService,
    private val mangaDao: MangaDao
){
    suspend fun fetchManga(page: Int): List<Manga> {
        return try {

            val response = mangaApiService.fetchMangaData(page=page)

            if(response.isSuccessful){
                val mangasList = response.body()?.data ?: emptyList()


                // Check if any manga has null fields
                mangasList.forEach { manga ->

                    if (manga.title == null || manga.thumb == null) {
                        Log.e("MangaRepo", "Null field detected in manga: $manga")
                    }
                }
                if (page == 1) {
                    withContext(Dispatchers.IO) {
                        mangaDao.clearAll()
                    }
                }

                // ✅ Insert off the main thread
                withContext(Dispatchers.IO) {
                    mangaDao.insertAll(mangasList)
                }
                mangasList
            } else mangaDao.getAll()
        } catch (e: Exception){
            Log.e("MangaRepo", "Error fetching manga: ${e.message}")
            withContext(Dispatchers.IO) {
                mangaDao.getAll()
            }
        }
    }

    suspend fun getMangaById(mangaId: String): Manga? {
        return mangaDao.getMangaById(mangaId)
    }
}