package ai.doubletapp.sample.adaptive.ui.features.notes

import ai.doubletapp.sample.adaptive.R
import ai.doubletapp.sample.adaptive.domain.model.Note
import ai.doubletapp.sample.adaptive.ui.core.empty.EmptyScreen
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalDisplayFeatures
import ai.doubletapp.sample.adaptive.ui.features.notes.detail.NoteDetail
import ai.doubletapp.sample.adaptive.ui.features.notes.list.NoteList
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.ContentType
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationType
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.key
import com.google.accompanist.adaptive.HorizontalTwoPaneStrategy
import com.google.accompanist.adaptive.TwoPane

@Composable
fun NotesUi(
    contentType: ContentType,
    navigationType: NavigationType,
    state: NotesUiState,
    onSelectNote: (Long) -> Unit,
    closeDetail: () -> Unit
) {
    val notesLazyListState = rememberLazyListState()
    val noteDetailScrollStates = when (state) {
        NotesUiState.NoNotes -> emptyList()
        is NotesUiState.HasNotes -> state.notes
    }.associate { note ->
        key(note.id) {
            note.id to rememberScrollState()
        }
    }

    if (contentType == ContentType.DUAL_PANE) {
        when (state) {
            NotesUiState.NoNotes -> EmptyNotes()
            is NotesUiState.HasNotes -> DualPane(
                notes = state.notes,
                selectedNote = state.selectedNote,
                notesLazyListState = notesLazyListState,
                noteDetailScrollStates = noteDetailScrollStates,
                onSelectNote = onSelectNote
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                NotesUiState.NoNotes -> EmptyNotes()
                is NotesUiState.HasNotes -> SinglePane(
                    state = state,
                    notesLazyListState = notesLazyListState,
                    noteDetailScrollState = noteDetailScrollStates.getValue(state.selectedNote.id),
                    onSelectNote = onSelectNote,
                    closeDetail = closeDetail
                )
            }
            if (navigationType == NavigationType.BOTTOM_NAVIGATION) {
                FloatingActionButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(16.dp),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = stringResource(id = R.string.edit),
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun DualPane(
    notes: List<Note>,
    selectedNote: Note,
    notesLazyListState: LazyListState,
    noteDetailScrollStates: Map<Long, ScrollState>,
    onSelectNote: (Long) -> Unit
) {
    TwoPane(
        first = {
            NoteList(
                notes = notes,
                selectedNote = selectedNote,
                lazyListState = notesLazyListState,
                onSelectNote = onSelectNote
            )
        },
        second = {
            NoteDetail(
                note = selectedNote,
                scrollState = noteDetailScrollStates.getValue(selectedNote.id),
                isFullScreen = false
            )
        },
        strategy = HorizontalTwoPaneStrategy(splitFraction = 0.4f, gapWidth = 16.dp),
        displayFeatures = LocalDisplayFeatures.current
    )
}

@Composable
private fun SinglePane(
    state: NotesUiState.HasNotes,
    notesLazyListState: LazyListState,
    noteDetailScrollState: ScrollState,
    onSelectNote: (Long) -> Unit,
    closeDetail: () -> Unit
) {
    if (state.isDetailOpen) {
        BackHandler(onBack = closeDetail)
        NoteDetail(
            note = state.selectedNote,
            scrollState = noteDetailScrollState,
            onBackPressed = closeDetail
        )
    } else {
        NoteList(
            notes = state.notes,
            selectedNote = state.selectedNote,
            lazyListState = notesLazyListState,
            onSelectNote = onSelectNote
        )
    }
}

@Composable
private fun EmptyNotes() {
    EmptyScreen(text = stringResource(id = R.string.no_notes))
}
