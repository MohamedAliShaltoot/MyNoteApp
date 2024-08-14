package com.example.mynoteapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.example.mynoteapp.data.db.Note
import com.example.mynoteapp.ui.theme.MynoteAppTheme

class MainScreen(val noteViewModel:NoteViewModel): Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {


        val index= remember { mutableStateOf(0) }
        val showDialog= remember { mutableStateOf(false) }
        MynoteAppTheme {
            Scaffold(

                topBar = {
                    TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { }) {
                            Icon(imageVector = Icons.Default.ArrowBack,contentDescription = null, tint = Color.White)
                        }
                    },
                    actions = {
                        IconButton(onClick = {


                        }) {
                            Icon(imageVector = Icons.Default.MoreVert,contentDescription = null, tint = Color.White)
                        }
                    }

                    ,colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.DarkGray)
                    ,title = {
                            Text(text = " Notes", color = Color.White)
                         }
                )
                }, floatingActionButton = {
                    IconButton(modifier = Modifier.size(60.dp),colors = IconButtonDefaults.iconButtonColors(containerColor = Color.DarkGray),onClick = {  showDialog.value=true}) {
                        Icon(modifier = Modifier.size(35.dp),imageVector = Icons.Default.Add,contentDescription = null, tint =  Color.White)
                    }
                },
                bottomBar ={
                    BottomAppBar(containerColor = Color.DarkGray) {
                        IconButton(modifier = Modifier.weight(1f),onClick = {index.value=0  }) {
                            Column {
                                Icon(modifier = Modifier.size(35.dp),imageVector = Icons.Default.Home,contentDescription = null, tint =if (index.value==0) Color.Black else Color.White)
                                if (index.value==0){
                                    Text(text = "home")
                                }
                            }

                        }
                        IconButton(modifier = Modifier.weight(1f),onClick = {index.value=1  }) {
                            Icon(modifier = Modifier.size(35.dp),imageVector = Icons.Default.Favorite,contentDescription = null, tint =if (index.value==1) Color.Black else Color.White)
                        }
                    }
                }

                ,modifier = Modifier.fillMaxSize()) { innerPadding->


                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding) ) {
                    if (showDialog.value){
                        AddNoteDialog(onAddNote = {title,description->
                            noteViewModel.insertNote(Note(title=title, description = description, isFavorite = false))
                            showDialog.value=false
                        }, onDissmissRequest = {showDialog.value=false})
                    }

                    if(index.value==0){
                        NotesScreenContent(noteViewModel)
                    }else {
                        FavouriteScreenContent(noteViewModel)
                    }
                }

            }
        }
    }

}