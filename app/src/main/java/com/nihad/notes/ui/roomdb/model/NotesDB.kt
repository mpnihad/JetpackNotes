package com.nihad.notes.ui.roomdb.model

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.OffsetDateTime
import javax.inject.Inject


@Entity(
    tableName = "notes",
    indices = [
        Index("id", unique = true)
    ]
)

@Immutable
data class NotesDB
(
    @PrimaryKey(autoGenerate = true) @ColumnInfo (name = "id") val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "is_attachment") val is_attachment: Boolean? = null,
    @ColumnInfo(name = "checklist") val checklist: String? = null,
    @ColumnInfo(name = "category_ids") val category_id: String? = null,
    @ColumnInfo(name = "reminder") val reminder: String? = null,
    @ColumnInfo(name = "reminder_status") val reminder_status: String? = null,
    @ColumnInfo(name = "date") val date: OffsetDateTime,
)