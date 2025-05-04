package com.example.mangafaceapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mangafaceapp.data.model.Manga

@Dao
interface MangaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(mangas: List<Manga>)

    @Query("SELECT * FROM manga")
    suspend fun getAll(): List<Manga>

    @Query("DELETE FROM manga")
    suspend fun clearAll()
}