package com.nihad.notes.ui.roomdb.dao



import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nihad.notes.ui.roomdb.model.AttachmentDB
import com.nihad.notes.ui.roomdb.model.NotesCategory

import kotlinx.coroutines.flow.Flow

/**
 * [Room] DAO for [AttachmentDao] related operations.
 */
@Dao
abstract class NotesCategoryDao {

    @Query("SELECT * FROM notes_category WHERE category_id = :id")
    abstract fun allNotesCategory(id: Int): Flow<NotesCategory>

//    @Transaction
//    @Query(
//        """
//        SELECT podcasts.*, last_episode_date, (followed_entries.podcast_uri IS NOT NULL) AS is_followed
//        FROM podcasts
//        INNER JOIN (
//            SELECT podcast_uri, MAX(published) AS last_episode_date
//            FROM episodes
//            GROUP BY podcast_uri
//        ) episodes ON podcasts.uri = episodes.podcast_uri
//        LEFT JOIN podcast_followed_entries AS followed_entries ON followed_entries.podcast_uri = episodes.podcast_uri
//        ORDER BY datetime(last_episode_date) DESC
//        LIMIT :limit
//        """
//    )
//    abstract fun podcastsSortedByLastEpisode(
//        limit: Int
//    ): Flow<List<PodcastWithExtraInfo>>
//
//
//


    @Query("SELECT COUNT(*) FROM notes_category")
    abstract suspend fun count(): Int

    /**
     * The following methods should really live in a base interface. Unfortunately the Kotlin
     * Compiler which we need to use for Compose doesn't work with that.
     * TODO: remove this once we move to a more recent Kotlin compiler
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: NotesCategory): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg entity: NotesCategory)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: Collection<NotesCategory>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: NotesCategory)

    @Delete
    abstract suspend fun delete(entity: NotesCategory): Int
}
