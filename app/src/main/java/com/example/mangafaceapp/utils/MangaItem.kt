package com.example.mangafaceapp.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.mangafaceapp.data.model.Manga


@Composable
fun MangaItem(manga: Manga, onClick: () -> Unit) {

    var imageLoadFailed by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(4.dp)
    ) {
        if (imageLoadFailed) {
            manga.title?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .size(150.dp)
                        .wrapContentSize(Alignment.Center)
                )
            }
        } else {
            AsyncImage(
                model = manga.thumb ?: "https://via.placeholder.com/150",
                contentDescription = manga.title,
                modifier = Modifier.size(150.dp),
                onError = {
                    imageLoadFailed = true
                }
            )
        }
    }


}
