package com.nihad.notes.ui.notes.main

import com.nihad.notes.ui.roomdb.dao.NotesDao
import com.nihad.notes.ui.roomdb.model.NotesDB
import com.nihad.notes.ui.roomdb.utils.DataState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AddNotesRepository
constructor(
    private val notesDao: NotesDao,

)
{
    suspend fun addNotes(notesDB: NotesDB): Flow<DataState<String>> = flow {
        emit(DataState.Loading)
        try{

                val notesId=notesDao.insert(notesDB)

            if(notesId==-1)
            {

                emit(DataState.ErrorSave("Save Unsuccessfull"))
            }
            emit(DataState.Success("Save successfull"))
        }catch (e: Exception){
            emit(DataState.Error(e))
        }
    }
}