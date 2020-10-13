package com.nihad.notes.ui.notes.repository


import com.nihad.notes.ui.attachment.store.AttachmentStore
import com.nihad.notes.ui.category.store.CategoryStore
import com.nihad.notes.ui.notes.store.NoteStore
import com.nihad.notes.ui.roomdb.TransactionRunner
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class NotesRepository(
    private val noteStore: NoteStore,
    private val attachmentStore: AttachmentStore,
    private val categoryStore: CategoryStore,
    private val transactionRunner: TransactionRunner,
    private val mainDispatcher: CoroutineDispatcher
) {
    private var refreshingJob: Job? = null

    private val scope = CoroutineScope(mainDispatcher)

    suspend fun updatePodcasts(force: Boolean) {
        if (refreshingJob?.isActive == true) {
            refreshingJob?.join()
        } else if (force || noteStore.isEmpty()) {
            refreshingJob = scope.launch {

            }
        }
    }
}
