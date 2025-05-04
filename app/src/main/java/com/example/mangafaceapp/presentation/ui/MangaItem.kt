package com.example.mangafaceapp.presentation.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mangafaceapp.data.model.Manga


@Composable
fun MangaItem(manga: Manga, onClick: () -> Unit) {

        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(4.dp)) {
            AsyncImage(
                model = manga.thumb?: "https://via.placeholder.com/150",
                contentDescription = manga.title,
                modifier = Modifier.size(150.dp)
            )
        }

}
