package com.nihad.notes.ui.notes.model


import androidx.room.Embedded
import com.nihad.notes.ui.roomdb.model.AttachmentDB
import com.nihad.notes.ui.roomdb.model.NotesCategory
import com.nihad.notes.ui.roomdb.model.NotesDB

import java.util.Objects

class NotesWithAllInfo {
    @Embedded
    lateinit var notes: NotesDB

    @Embedded
    lateinit var attachmentDB: AttachmentDB

    @Embedded
    lateinit var notesCategory: NotesCategory



    /**
     * Allow consumers to destructure this class
     */
    operator fun component1() =  notes
    operator fun component2() = attachmentDB
    operator fun component3() = notesCategory

    override fun equals(other: Any?): Boolean = when {
        other === this -> true
        other is NotesWithAllInfo -> {
            notes == other.notes &&
                    attachmentDB == other.attachmentDB &&
                    notesCategory == other.notesCategory
        }
        else -> false
    }

    override fun hashCode(): Int = Objects.hash( notes ,attachmentDB ,notesCategory)
}
