package com.pardess.notes.di

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Room
import com.pardess.notes.data.NoteDatabase
import com.pardess.notes.data.NoteDatabaseDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideNotesDao(noteDatabase: NoteDatabase): NoteDatabaseDao = noteDatabase.noteDao()

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase =
        Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "notes_db"
        ).fallbackToDestructiveMigration()
            .build()



}