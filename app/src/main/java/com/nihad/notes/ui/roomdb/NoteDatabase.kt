package com.nihad.notes.ui.roomdb



import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nihad.notes.ui.roomdb.dao.AttachmentDao
import com.nihad.notes.ui.roomdb.dao.NotesCategoryDao
import com.nihad.notes.ui.roomdb.dao.NotesDao
import com.nihad.notes.ui.roomdb.model.AttachmentDB
import com.nihad.notes.ui.roomdb.model.NotesCategory
import com.nihad.notes.ui.roomdb.model.NotesDB
import com.nihad.notes.ui.roomdb.utils.DateTimeTypeConverters

@Database(
    entities = [
        NotesDB::class,
        AttachmentDB::class,
        NotesCategory::class,

    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(DateTimeTypeConverters::class)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    abstract fun attachmentDao(): AttachmentDao
    abstract fun notesCategoryDao(): NotesCategoryDao
    abstract fun transactionRunnerDao(): TransactionRunnerDao
    companion object{
        val DATABASE_NAME: String = "noteDb"
    }
}
