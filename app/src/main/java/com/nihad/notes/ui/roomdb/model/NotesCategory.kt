package com.nihad.notes.ui.roomdb.model







import androidx.compose.runtime.Immutable
import androidx.room.*


@Entity(
    tableName = "notes_category",
    foreignKeys = [
        ForeignKey(
            entity = NotesDB::class,
            parentColumns = ["id"],
            childColumns = ["category_notes_id"],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        ),

    ],
    indices = [

        Index("category_id", unique = true),
        Index("category_notes_id")
    ]
)

@Immutable
data class NotesCategory(
    @PrimaryKey(autoGenerate = true) @ColumnInfo (name = "category_id") val id: Int,
    @ColumnInfo(name = "category_notes_id") val notes_id: Int,
    @ColumnInfo(name = "category_name") val category_name: String? = null,
   )