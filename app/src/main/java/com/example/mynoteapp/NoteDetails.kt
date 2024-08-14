package com.example.mynoteapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.mynoteapp.data.db.Note

class NoteDetails(val note: Note,val noteViewModel: NoteViewModel) : Screen {
    @Composable
    override fun Content() {
        val title = remember {
            mutableStateOf(note.title)
        }

   val description = remember {
       mutableStateOf(note.description)
   }
        val navigator= LocalNavigator.currentOrThrow
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
          IconButton(onClick = {navigator.pop() }, modifier =Modifier.size(70.dp)) {
           Icon(imageVector = Icons.Default.ArrowBack, contentDescription =null, tint = Color.Black )
          }

           OutlinedTextField(value = title.value, onValueChange ={
               title.value=it
           })

            Spacer(modifier = Modifier.height(5.dp))
             OutlinedTextField(value = description.value, onValueChange ={
               description.value=it
           }
           )
            Spacer(modifier = Modifier.height(10.dp))
            Row {
                Button(onClick = { noteViewModel.updateNote(note.copy(title=title.value, description  =description.value))
                    navigator.pop()
                }, colors = ButtonDefaults.buttonColors(Color.Black)) {
                 Text(text = " Update Note")
                }
                Spacer(modifier = Modifier.width(15.dp))
                Button(onClick = { noteViewModel.deleteNote(note)
                navigator.pop()
                }, colors = ButtonDefaults.buttonColors(Color.Black)

                ) {
                    Text(text = " Delete Note")
                }

            }
        }
    }
}