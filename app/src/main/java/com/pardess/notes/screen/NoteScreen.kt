package com.pardess.notes.screen

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pardess.notes.R
import com.pardess.notes.components.NoteButton
import com.pardess.notes.components.NoteInputText
import com.pardess.notes.data.NotesDataSource
import com.pardess.notes.model.Note
import com.pardess.notes.util.formatDate

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current

    Column {
        Column(modifier = Modifier.padding(6.dp)) {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) },
                actions = {
                    Icon(
                        imageVector = Icons.Rounded.Notifications,
                        contentDescription = "Icon"
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFDADFE3)
                )
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = title, label = "Title", onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) title = it
                }
            )
            NoteInputText(
                modifier = Modifier.padding(
                    top = 9.dp,
                    bottom = 8.dp
                ),
                text = description, label = "Add a note", onTextChange = {
                    if (it.all { char ->
                            char.isLetter() || char.isWhitespace()
                        }) description = it
                }
            )
            NoteButton(text = "Save", onClick = {
                if (title.isNotEmpty() && description.isNotEmpty()) {
                    onAddNote(Note(title = title, description = description))
                    Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
                    title = ""
                    description = ""
                }
            })
        }
        Divider(modifier = Modifier.padding(10.dp))
        if (notes.isNotEmpty()) {
            LazyColumn {
                items(notes) { note ->
                    NoteRow(note = note,
                        onNoteClicked = {
                            onRemoveNote(it)

                        })
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit,
) {
    Surface(
        modifier
            .padding(4.dp)
            .clip(RoundedCornerShape(topEnd = 33.dp, bottomStart = 33.dp))
            .fillMaxWidth(),
        color = Color(0xFFDFE6EB),
        tonalElevation = 10.dp
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            Column(
                modifier
//                    .clickable { onNoteClicked(note) }
                    .padding(horizontal = 20.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = note.title,
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = note.description,
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = formatDate(note.entryDate.time),
                    style = MaterialTheme.typography.bodySmall
                )
            }
            IconButton(onClick = { onNoteClicked(note) }) {
                Icon(modifier = Modifier.size(30.dp).padding(end = 5.dp),
                    imageVector = Icons.Default.Close, contentDescription = "Delete")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun NotesScreenPreview() {
    NoteScreen(notes = NotesDataSource().loadNotes(), onAddNote = {}, onRemoveNote = {})
}