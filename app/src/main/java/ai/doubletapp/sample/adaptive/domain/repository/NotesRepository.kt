package ai.doubletapp.sample.adaptive.domain.repository

import ai.doubletapp.sample.adaptive.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NotesRepository {

    fun getNotes(): Flow<List<Note>>
}
