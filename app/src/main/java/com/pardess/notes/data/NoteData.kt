package com.pardess.notes.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.pardess.notes.model.Note

@RequiresApi(Build.VERSION_CODES.O)
class NotesDataSource {

    fun loadNotes(): List<Note> {
        return listOf(
            Note(title = "A good day", description = "We went on a vacation"),
            Note(title = "B good day", description = "We went on a vacation"),
            Note(title = "C good day", description = "We went on a vacation"),
        )
    }

}