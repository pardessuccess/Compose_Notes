package com.pardess.notes

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pardess.notes.screen.NoteScreen
import com.pardess.notes.screen.NoteViewModel
import com.pardess.notes.ui.theme.Compose3Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose3Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val noteViewModel: NoteViewModel by viewModels()
                    NotesApp(noteViewModel)
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NotesApp(noteViewModel: NoteViewModel = viewModel()) {
    val noteList = noteViewModel.noteList.collectAsState().value

    NoteScreen(
        notes = noteList,
        onAddNote = { noteViewModel.addNote(it) },
        onRemoveNote = {
            noteViewModel.removeNote(it)
        })
}