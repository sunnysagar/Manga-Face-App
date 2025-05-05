package com.example.mangafaceapp.domain

import android.util.Log
import androidx.compose.runtime.State
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

    private val _selectedManga = mutableStateOf<Manga?>(null)
    val selectedManga: State<Manga?> = _selectedManga

    fun loadMore() {
        if (isLoading || endReached) return

        viewModelScope.launch {
            isLoading = true

            // Fetch new data from repository
            val newData = repository.fetchManga(currentPage)

            // Log to check what data is fetched
            Log.d("MangaViewModel", "Fetched new data: $newData")

            // Check if new data is not empty
            if (newData.isNotEmpty()) {
                Log.d("MangaViewModel", "New data added: ${newData.size} items")
                mangaList.addAll(newData)
                currentPage++
            } else {
                Log.d("MangaViewModel", "No more data found, end reached.")
                endReached = true
            }

            isLoading = false
        }
    }

    fun loadMangaById(mangaId: String) {
        viewModelScope.launch {
            _selectedManga.value = repository.getMangaById(mangaId)
        }
    }


    fun reload() {
        currentPage = 1
        endReached = false
        mangaList.clear()
        loadMore()
    }
}