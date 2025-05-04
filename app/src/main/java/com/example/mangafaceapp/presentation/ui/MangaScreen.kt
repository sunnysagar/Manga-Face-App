package com.example.mangafaceapp.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController

@Composable
fun MangaScreen(navController: NavController, viewModel: MangaViewModel = hiltViewModel()){
    val mangas = viewModel.mangaList
    val listState = rememberLazyListState()

    // Call this once when the screen appears
    LaunchedEffect(Unit) {
        viewModel.loadMore()
    }

    LaunchedEffect(listState) {
        snapshotFlow { listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { index ->
                if(index == mangas.lastIndex) viewModel.loadMore()
            }
    }

    LazyColumn ( state = listState ) {
        itemsIndexed(mangas) { index, manga ->
            MangaItem(manga = manga, onClick = {
                navController.navigate("mangaDetail/${manga.id}")
            })
        }

        if (viewModel.isLoading) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}