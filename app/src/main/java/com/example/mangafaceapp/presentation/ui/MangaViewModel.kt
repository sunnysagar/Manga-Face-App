package com.example.mangafaceapp.presentation.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mangafaceapp.data.model.Manga
import com.example.mangafaceapp.data.repository.MangaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MangaViewModel @Inject constructor(
    private val repository: MangaRepository
): ViewModel() {

    var mangaList = mutableStateListOf<Manga>()
    private var currentPage = 1
    var isLoading by mutableStateOf(false)
    var endReached by mutableStateOf(false)

//    fun loadMore() {
//        if(isLoading || endReached) return
//
//        viewModelScope.launch {
//            isLoading = true
//            val newData = repository.fetchManga(currentPage)
//            if(newData.isNotEmpty()){
//                mangaList.addAll(newData)
//                currentPage++
//            } else {
//                endReached = true
//            }
//            isLoading = false
//        }
//    }

    fun loadMore() {
        mangaList.clear()
        mangaList.addAll(
            listOf(
                Manga(
                    id = "1",
                    title = "Test Manga",
                    subTitle = "ok",
                    status = "ongoing",
                    thumb = "",
                    summary = "This is a test.",
                    authors = listOf("Author A"),
                    genres = listOf("Action", "Adventure"),
                    nsfw = false,
                    type = "korea",
                    totalChapter = 10,
                    createdAt = System.currentTimeMillis(),
                    updatedAt = System.currentTimeMillis(),
                )
            )
        )
    }


    fun reload() {
        currentPage = 1
        endReached = false
        mangaList.clear()
        loadMore()
    }
}