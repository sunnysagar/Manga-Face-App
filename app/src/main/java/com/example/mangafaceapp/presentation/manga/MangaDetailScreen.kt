package com.example.mangafaceapp.presentation.manga

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.mangafaceapp.domain.MangaViewModel

@Composable
fun MangaDetailScreen(
    mangaId: String,
    navController: NavController,
    viewModel: MangaViewModel = hiltViewModel()
) {

    LaunchedEffect(mangaId) {
        viewModel.loadMangaById(mangaId)
    }

    val manga = viewModel.selectedManga.value
    manga?.let {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(modifier = Modifier.padding(4.dp)){
                AsyncImage(
                    model = it.thumb,
                    contentDescription = it.title,
                    modifier = Modifier.size(150.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Column(modifier = Modifier.padding(4.dp)
                    .weight(1f)    // This ensures column gets remaining space
                ) {
                    it.title?.let { it1 -> Text(it1, style = MaterialTheme.typography.titleLarge) }
                    Spacer(modifier = Modifier.height(4.dp))
                    it.sub_title?.let{ it1 -> Text(it1, style = MaterialTheme.typography.titleMedium) }
                    Log.d("SubtitleCheck", "Subtitle: ${it.sub_title}")

                }
                }


            Spacer(modifier = Modifier.height(4.dp))
            it.summary?.let { it1 -> Text(it1) }
            Spacer(modifier = Modifier.height(6.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }
    }

}
