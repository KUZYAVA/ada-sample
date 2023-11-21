package ai.doubletapp.sample.adaptive.ui.features.notes

import ai.doubletapp.sample.adaptive.domain.model.Note

sealed interface NotesUiState {

    object NoNotes : NotesUiState

    data class HasNotes(
        val notes: List<Note>,
        val selectedNote: Note,
        val isDetailOpen: Boolean
    ) : NotesUiState
}
