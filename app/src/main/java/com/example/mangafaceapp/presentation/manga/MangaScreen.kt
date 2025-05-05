package com.example.mangafaceapp.presentation.manga

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mangafaceapp.utils.MangaItem
import com.example.mangafaceapp.domain.MangaViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MangaScreen(navController: NavController, viewModel: MangaViewModel = hiltViewModel()){
    val mangas = viewModel.mangaList
    val gridState = rememberLazyGridState()

    // Call this once when the screen appears
    LaunchedEffect(Unit) {
        viewModel.loadMore()
    }

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { index ->
                if(index != null && index == mangas.lastIndex) viewModel.loadMore()
            }
    }

    LazyVerticalGrid (
        columns = GridCells.Fixed(3),
        state = gridState,
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        itemsIndexed(mangas) { _, manga ->
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