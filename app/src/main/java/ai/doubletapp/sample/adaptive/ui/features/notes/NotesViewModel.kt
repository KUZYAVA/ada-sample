package ai.doubletapp.sample.adaptive.ui.features.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ai.doubletapp.sample.adaptive.data.repository.NotesRepositoryImpl
import ai.doubletapp.sample.adaptive.domain.model.Note
import ai.doubletapp.sample.adaptive.domain.repository.NotesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private data class NotesViewModelState(
    val notes: List<Note>? = null,
    val selectedNoteId: Long? = null,
    val isDetailOpen: Boolean = false
) {

    fun toUiState(): NotesUiState =
        if (notes.isNullOrEmpty()) {
            NotesUiState.NoNotes
        } else {
            NotesUiState.HasNotes(
                notes = notes,
                selectedNote = notes.find {
                    it.id == selectedNoteId
                } ?: notes.first(),
                isDetailOpen = isDetailOpen
            )
        }
}

class NotesViewModel(
    private val notesRepository: NotesRepository = NotesRepositoryImpl()
) : ViewModel() {

    private val viewModelState = MutableStateFlow(NotesViewModelState())

    val uiState = viewModelState
        .map(NotesViewModelState::toUiState)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = viewModelState.value.toUiState()
        )

    init {
        observeNotes()
    }

    private fun observeNotes() {
        viewModelScope.launch {
            notesRepository.getNotes().collect { notes ->
                viewModelState.update { it.copy(notes = notes) }
            }
        }
    }

    fun onSelectNote(noteId: Long) {
        viewModelState.update {
            it.copy(
                selectedNoteId = noteId,
                isDetailOpen = true
            )
        }
    }

    fun closeDetail() {
        viewModelState.update {
            it.copy(
                selectedNoteId = null,
                isDetailOpen = false
            )
        }
    }
}
