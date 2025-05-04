package com.example.mangafaceapp.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage

@Composable
fun MangaDetailScreen(mangaId: String, navController: NavController, viewModel: MangaViewModel = hiltViewModel()) {
    val manga = viewModel.mangaList.find { it.id.toString() == mangaId }
    manga?.let {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(it.title, style = MaterialTheme.typography.titleLarge)
            AsyncImage(
                model = it.thumb,
                contentDescription = it.title,
                modifier = Modifier.size(150.dp).fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(it.summary)

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
        }
    }

}
