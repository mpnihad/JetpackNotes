package com.nihad.notes.ui.notes.main

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nihad.notes.ui.roomdb.model.NotesDB
import com.nihad.notes.ui.roomdb.utils.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class AddNotesViewModel
@ViewModelInject
constructor(
    private val addNotesRepository: AddNotesRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _dataState: MutableLiveData<DataState<String>> = MutableLiveData()

    val dataState: LiveData<DataState<String>>
        get() = _dataState

    fun setStateEvent(mainStateEvent: AddNoteStateEvent, notesDB: NotesDB){



        viewModelScope.launch {
            when(mainStateEvent){
                is AddNoteStateEvent.AddNewNotes -> {

                    addNotesRepository.addNotes(notesDB)
                        .onEach {dataState ->
                            _dataState.value = dataState
                        }
                        .launchIn(viewModelScope)
                }

                AddNoteStateEvent.None -> {
                    // who cares
                }
            }
        }
    }
}

sealed class AddNoteStateEvent{

    class AddNewNotes : AddNoteStateEvent()

    object None: AddNoteStateEvent()
}