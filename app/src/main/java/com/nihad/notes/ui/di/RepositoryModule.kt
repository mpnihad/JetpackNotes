package com.nihad.notes.ui.di

import com.nihad.notes.ui.notes.main.AddNotesRepository
import com.nihad.notes.ui.roomdb.dao.NotesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideMainRepository(
        notesDao: NotesDao,

    ): AddNotesRepository{
        return AddNotesRepository(notesDao)
    }
}














