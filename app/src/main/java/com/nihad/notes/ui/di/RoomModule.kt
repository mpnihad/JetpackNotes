package com.nihad.notes.ui.di

import android.content.Context
import androidx.room.Room
import com.nihad.notes.ui.roomdb.NoteDatabase
import com.nihad.notes.ui.roomdb.TransactionRunnerDao
import com.nihad.notes.ui.roomdb.dao.AttachmentDao
import com.nihad.notes.ui.roomdb.dao.NotesCategoryDao
import com.nihad.notes.ui.roomdb.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object RoomModule {

    @Singleton
    @Provides
    fun provideBlogDb(@ApplicationContext context: Context): NoteDatabase {
        return Room
            .databaseBuilder(
                context,
                NoteDatabase::class.java,
                NoteDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideNoteDAO(noteDatabase: NoteDatabase): NotesDao {
        return noteDatabase.notesDao()
    }

    @Singleton
    @Provides
    fun provideAttachemntDAO(noteDatabase: NoteDatabase): AttachmentDao {
        return noteDatabase.attachmentDao()
    }

    @Singleton
    @Provides
    fun provideCategoryDAO(noteDatabase: NoteDatabase): NotesCategoryDao {
        return noteDatabase.notesCategoryDao()
    }
    @Singleton
    @Provides
    fun provideTransactionDAO(noteDatabase: NoteDatabase): TransactionRunnerDao {
        return noteDatabase.transactionRunnerDao()
    }
}