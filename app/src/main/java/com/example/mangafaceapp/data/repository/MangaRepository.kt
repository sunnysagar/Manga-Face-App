package com.example.mangafaceapp.data.repository

import com.example.mangafaceapp.data.dao.MangaDao
import com.example.mangafaceapp.data.model.Manga
import com.example.mangafaceapp.data.remote.MangaApiService
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
                if(page == 1) mangaDao.clearAll()   // Refersh only on first page

                    // save to Room
                mangaDao.insertAll(mangasList)
                mangasList
            } else mangaDao.getAll()
        } catch (e: Exception){
            mangaDao.getAll()   // fallback to cache
        }
    }
}