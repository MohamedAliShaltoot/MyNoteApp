package com.example.mynoteapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.Navigator

import com.example.mynoteapp.data.db.Note
import com.example.mynoteapp.ui.theme.MynoteAppTheme


class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  enableEdgeToEdge()


        setContent {
            val noteViewModel = remember { NoteViewModel(application) }

          Navigator(screen = MainScreen(noteViewModel))

        }
    }
}
@Composable
fun AddNoteDialog(onAddNote: (String,String)->Unit,onDissmissRequest: ()->Unit){
    val title= remember {
        mutableStateOf("")
    }
    val description= remember {
        mutableStateOf("")
    }
   AlertDialog(containerColor = AlertDialogDefaults.containerColor,onDismissRequest = { onDissmissRequest()}, confirmButton = {
       Button(enabled = title.value.isNotEmpty()&&description.value.isNotEmpty(),onClick = {onAddNote(title.value,description.value) }) {
           Text(text = "Add Note")
       }
   }, title = { Text(text = " Add Note")},
       text = {
           Column {
             OutlinedTextField(placeholder = { Text(text = "title")},value = title.value, onValueChange = {title.value=it})
               Spacer(modifier = Modifier.height(10.dp))
               OutlinedTextField(placeholder ={Text(text = "description")},value = description.value, onValueChange = {description.value=it})
           }
       })


}



@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MynoteAppTheme {

    }
        Greeting("Android")

}




























