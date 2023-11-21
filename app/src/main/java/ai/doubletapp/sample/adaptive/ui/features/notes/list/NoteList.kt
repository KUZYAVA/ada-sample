package ai.doubletapp.sample.adaptive.ui.features.notes.list

import ai.doubletapp.sample.adaptive.domain.model.Note
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun NoteList(
    notes: List<Note>,
    selectedNote: Note?,
    lazyListState: LazyListState,
    onSelectNote: (Long) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        DockedSearchBar(
            notes = notes,
            onSearchItemSelected = { searchedNote ->
                onSelectNote(searchedNote.id)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 80.dp),
            state = lazyListState
        ) {
            items(items = notes, key = { it.id }) { note ->
                NoteListItem(
                    note = note,
                    isSelected = selectedNote?.id == note.id,
                    onSelectNote = onSelectNote
                )
            }
        }
    }
}

@Composable
private fun NoteListItem(
    note: Note,
    isSelected: Boolean,
    onSelectNote: (Long) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(CardDefaults.shape)
            .clickable { onSelectNote(note.id) }
            .clip(CardDefaults.shape),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Text(
                text = note.subject,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
            )
            Text(
                text = note.text,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
