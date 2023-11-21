package ai.doubletapp.sample.adaptive.ui.features.notes

import ai.doubletapp.sample.adaptive.ui.features.main.components.models.ContentType
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NotesScreen(
    contentType: ContentType,
    navigationType: NavigationType
) {
    val viewModel = viewModel<NotesViewModel>()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    NotesUi(
        contentType = contentType,
        navigationType = navigationType,
        state = uiState,
        onSelectNote = viewModel::onSelectNote,
        closeDetail = viewModel::closeDetail
    )
}
