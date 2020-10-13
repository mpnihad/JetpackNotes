package com.nihad.notes.ui.roomdb.model


import androidx.compose.runtime.Immutable
import androidx.room.*


@Entity(
    tableName = "attachment",
    foreignKeys = [
        ForeignKey(
            entity = NotesDB::class,
            parentColumns = ["id"],
            childColumns = ["attachment_notes_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),

    ],
    indices = [

        Index("attachment_id", unique = true),
        Index("attachment_notes_id")
    ]
)

@Immutable
data class AttachmentDB(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "attachment_id") val id: Int,
    @ColumnInfo(name = "attachment_notes_id") val notes_id: Int,
    @ColumnInfo(name = "attachment_loc") val attachment_loc: String? = null,
    @ColumnInfo(name = "attachment_type") val attachment_type: String? = null,
)