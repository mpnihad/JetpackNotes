package com.nihad.notes.ui.attachment.store

import com.nihad.notes.ui.roomdb.dao.AttachmentDao
import com.nihad.notes.ui.roomdb.dao.NotesDao

import com.nihad.notes.ui.notes.model.NotesWithAllInfo
import com.nihad.notes.ui.roomdb.model.AttachmentDB
import com.nihad.notes.ui.roomdb.model.NotesDB
import kotlinx.coroutines.flow.Flow


class AttachmentStore( private val attachmentDao: AttachmentDao, private val  noteDao: NotesDao) {


    suspend fun addNotes(notes: NotesDB) {
        noteDao.insert(notes)
    }

    suspend fun addAttachemnt(attachment: AttachmentDB) {
        attachmentDao.insert(attachment)
    }

    fun allNotesSortedByDate(
        limit: Int = Int.MAX_VALUE
    ): Flow<List<NotesWithAllInfo>> {
        return noteDao.allNotesSortedByDate(limit)
    }


    suspend fun isEmpty(): Boolean = noteDao.count() == 0
}




