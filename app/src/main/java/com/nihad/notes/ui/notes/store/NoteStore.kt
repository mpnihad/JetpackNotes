package com.nihad.notes.ui.notes.store

import com.nihad.notes.ui.notes.model.NotesWithAllInfo
import com.nihad.notes.ui.roomdb.TransactionRunner
import com.nihad.notes.ui.roomdb.dao.AttachmentDao
import com.nihad.notes.ui.roomdb.dao.NotesCategoryDao
import com.nihad.notes.ui.roomdb.dao.NotesDao
import com.nihad.notes.ui.roomdb.model.NotesDB
import kotlinx.coroutines.flow.Flow

class NoteStore(
    private val noteDao: NotesDao,
    private val attachmentDao: AttachmentDao,
    private val notesCategoryDao: NotesCategoryDao,
    private val transactionRunner: TransactionRunner
) {

    suspend fun addNotes(notes: NotesDB) {
        noteDao.insert(notes)
    }

    fun allNotesSortedByDate(
        limit: Int = Int.MAX_VALUE
    ): Flow<List<NotesWithAllInfo>> {
        return noteDao.allNotesSortedByDate(limit)
    }


    suspend fun isEmpty(): Boolean = noteDao.count() == 0

}
