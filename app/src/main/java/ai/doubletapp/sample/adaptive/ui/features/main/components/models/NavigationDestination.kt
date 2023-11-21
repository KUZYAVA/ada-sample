package ai.doubletapp.sample.adaptive.ui.features.main.components.models

import ai.doubletapp.sample.adaptive.R
import ai.doubletapp.sample.adaptive.ui.features.archive.ARCHIVE_SCREEN
import ai.doubletapp.sample.adaptive.ui.features.trash.TRASH_SCREEN
import ai.doubletapp.sample.adaptive.ui.features.notes.NOTES_SCREEN
import ai.doubletapp.sample.adaptive.ui.features.settings.SETTINGS_SCREEN
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Archive
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class NavigationDestination(
    val route: String,
    val selectedIcon: ImageVector,
    val iconTextId: Int
) {
    NOTES(
        route = NOTES_SCREEN,
        selectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.tab_notes
    ),
    ARCHIVE(
        route = ARCHIVE_SCREEN,
        selectedIcon = Icons.Outlined.Archive,
        iconTextId = R.string.tab_archive
    ),
    TRASH(
        route = TRASH_SCREEN,
        selectedIcon = Icons.Outlined.Delete,
        iconTextId = R.string.tab_trash
    ),
    SETTINGS(
        route = SETTINGS_SCREEN,
        selectedIcon = Icons.Outlined.Settings,
        iconTextId = R.string.tab_settings
    )
}
