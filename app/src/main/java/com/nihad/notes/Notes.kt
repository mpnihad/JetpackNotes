/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */



import android.content.Context
import androidx.room.Room
import com.nihad.notes.ui.attachment.store.AttachmentStore
import com.nihad.notes.ui.category.store.CategoryStore
import com.nihad.notes.ui.notes.repository.NotesRepository
import com.nihad.notes.ui.notes.store.NoteStore
import com.nihad.notes.ui.roomdb.NoteDatabase
import com.nihad.notes.ui.roomdb.TransactionRunner

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

import java.io.File

/**
 * A very simple global singleton dependency graph.
 *
 * For a real app, you would use something like Hilt/Dagger instead.
 */
object Notes {
//    lateinit var okHttpClient: OkHttpClient
//        private set

    lateinit var database: NoteDatabase
        private set


    val transactionRunner: TransactionRunner
        get() = database.transactionRunnerDao()



    val notesRepository by lazy {
        NotesRepository(

            noteStore = noteStore,
            attachmentStore = attachmentStore,
            categoryStore = categoryStore,
            transactionRunner = transactionRunner,
            mainDispatcher = mainDispatcher
        )
    }



    val noteStore by lazy {
        NoteStore(
            noteDao = database.notesDao(),
            attachmentDao = database.attachmentDao(),
            notesCategoryDao = database.notesCategoryDao(),
            transactionRunner = transactionRunner
        )
    }

    val attachmentStore by lazy {
        AttachmentStore(
            attachmentDao = database.attachmentDao(),
            noteDao = database.notesDao(),
        )
    }

    val categoryStore by lazy {
        CategoryStore(
            notesCategoryDao = database.notesCategoryDao(),

        )
    }

    val mainDispatcher: CoroutineDispatcher
        get() = Dispatchers.Main

    val ioDispatcher: CoroutineDispatcher
        get() = Dispatchers.IO

    fun provide(context: Context) {
//        okHttpClient = OkHttpClient.Builder()
//            .cache(Cache(File(context.cacheDir, "http_cache"), 20 * 1024 * 1024))
//            .apply {
//                if (BuildConfig.DEBUG) eventListenerFactory(LoggingEventListener.Factory())
//            }
//            .build()

        database = Room.databaseBuilder(context, NoteDatabase::class.java, "note.db")
            // This is not recommended for normal apps, but the goal of this sample isn't to
            // showcase all of Room.
            .fallbackToDestructiveMigration()
            .build()
    }
}
