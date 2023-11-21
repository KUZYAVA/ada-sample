package ai.doubletapp.sample.adaptive.data.repository

import ai.doubletapp.sample.adaptive.data.datasource.NotesDataSource
import ai.doubletapp.sample.adaptive.domain.repository.NotesRepository
import kotlinx.coroutines.flow.flow

class NotesRepositoryImpl : NotesRepository {

    override fun getNotes() = flow {
        val notes = NotesDataSource.notes
        emit(notes)
    }
}
