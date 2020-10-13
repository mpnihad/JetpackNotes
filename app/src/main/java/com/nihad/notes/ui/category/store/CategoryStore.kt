package com.nihad.notes.ui.category.store

import com.nihad.notes.ui.roomdb.dao.NotesCategoryDao
import com.nihad.notes.ui.roomdb.model.NotesCategory
import com.nihad.notes.ui.roomdb.model.NotesDB

class CategoryStore(private val notesCategoryDao: NotesCategoryDao) {
    suspend fun addCategory(notes: NotesCategory) {
        notesCategoryDao.insert(notes)
    }





    suspend fun isEmpty(): Boolean = notesCategoryDao.count() == 0

}

