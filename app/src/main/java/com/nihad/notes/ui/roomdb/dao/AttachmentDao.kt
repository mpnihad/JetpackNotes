package com.nihad.notes.ui.roomdb.dao



import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nihad.notes.ui.roomdb.model.AttachmentDB

import kotlinx.coroutines.flow.Flow

/**
 * [Room] DAO for [AttachmentDao] related operations.
 */
@Dao
abstract class AttachmentDao {

    @Query("SELECT * FROM attachment WHERE attachment_id = :id")
    abstract fun allAttachements(id: Int): Flow<AttachmentDB>

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


    @Query("SELECT COUNT(*) FROM attachment")
    abstract suspend fun count(): Int

    /**
     * The following methods should really live in a base interface. Unfortunately the Kotlin
     * Compiler which we need to use for Compose doesn't work with that.
     * TODO: remove this once we move to a more recent Kotlin compiler
     */

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(entity: AttachmentDB): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(vararg entity: AttachmentDB)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertAll(entities: Collection<AttachmentDB>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun update(entity: AttachmentDB)

    @Delete
    abstract suspend fun delete(entity: AttachmentDB): Int
}
