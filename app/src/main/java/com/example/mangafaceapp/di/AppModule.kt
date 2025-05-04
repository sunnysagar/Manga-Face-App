package com.example.mangafaceapp.di

import android.content.Context
import androidx.room.Room
import com.example.mangafaceapp.data.model.User
import com.example.mangafaceapp.data.dao.UserDao
import com.example.mangafaceapp.data.database.AppDatabase
import com.example.mangafaceapp.data.local.UserPreferences
import com.example.mangafaceapp.data.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "manga_face_app_db"
        ).build()
    }

    @Provides
    fun provideUserRepository(userDao: UserDao): UserRepository{
        return UserRepository(userDao)
    }

    @Provides
    fun providesUserDao(database: AppDatabase): UserDao{
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideUserPreferences(@ApplicationContext context: Context): UserPreferences {
        return UserPreferences(context)
    }
}