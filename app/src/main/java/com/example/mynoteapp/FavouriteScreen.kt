package com.example.mynoteapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun FavouriteScreenContent(noteViewModel: NoteViewModel){

    val notes=noteViewModel.allNotes.collectAsState()
    LazyColumn {
        items(notes.value?.filter { it.isFavorite==true }?: emptyList()){note->
            Card(elevation = CardDefaults.cardElevation(10.dp),modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(), colors= CardDefaults.cardColors(Color.Black)) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row {
                        Text(text = note.title, color = Color.White)
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(modifier = Modifier.clickable {
                            noteViewModel.updateNote(note.copy(isFavorite = !note.isFavorite))
                        },imageVector = Icons.Default.Star,contentDescription = null, tint =if(note.isFavorite) Color.Yellow else Color.White)
                    }

                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = note.description, color = Color.White)

                }
            }

        }
    }
    }

























