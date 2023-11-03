package com.pardess.notes.data

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pardess.notes.model.Note
import com.pardess.notes.util.DateConverter
import com.pardess.notes.util.UUIDConverter

@RequiresApi(Build.VERSION_CODES.O)
@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDatabaseDao

}