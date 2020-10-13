

package com.nihad.notes.ui.roomdb.dao

import androidx.room.*
import com.nihad.notes.ui.notes.model.NotesWithAllInfo
import com.nihad.notes.ui.roomdb.model.NotesDB
import kotlinx.coroutines.flow.Flow

/**
 * [Room] DAO for [NotesDao] related operations.
 */
@Dao
abstract class NotesDao {
    @Query("SELECT * FROM notes WHERE id = :id")
    abstract fun allNotes(id: Int): Flow<NotesDB>

    @Transaction
    @Query(
        """
        SELECT notes.*, attachment.*,notes_category.*
        FROM notes
        INNER JOIN (
            SELECT *
            FROM attachment
        ) attachment ON notes.id = attachment.attachment_notes_id
        LEFT JOIN (SELECT * FROM notes_category) notes_category ON notes.id=notes_category.category_notes_id
       ORDER BY datetime(date) DESC
        LIMIT :limit
        """
    )
    abstract fun allNotesSortedByDate(
        limit: Int
    ): Flow<List<NotesWithAllInfo>>





    @Query("SELECT COUNT(*) FROM notes")
    abstract suspend fun count(): Int

    /**
     * The following methods should really live in a base interface. Unfortunately the Kotlin
     * Compiler which we need to use for Compose doesn't work with that.
     * TODO: remove this once we move to a more recent Kotlin compiler
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: NotesDB): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg entity: NotesDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: Collection<NotesDB>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: NotesDB)

    @Delete
    abstract suspend fun delete(entity: NotesDB): Int
}
