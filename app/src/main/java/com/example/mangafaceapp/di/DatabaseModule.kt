package com.example.mangafaceapp.di

import android.content.Context
import androidx.room.Room
import com.example.mangafaceapp.data.dao.MangaDao
import com.example.mangafaceapp.data.database.AppDatabase
import com.example.mangafaceapp.data.database.MangaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): MangaDatabase {
        return Room.databaseBuilder(
            context,
            MangaDatabase::class.java,
            "manga_db"
        ).build()
    }

    @Provides
    fun provideMangaDao(database: MangaDatabase): MangaDao {
        return database.mangaDao()
    }
}
