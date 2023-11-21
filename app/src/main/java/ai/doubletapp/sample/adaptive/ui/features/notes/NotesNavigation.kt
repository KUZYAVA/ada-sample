package ai.doubletapp.sample.adaptive.ui.features.notes

import ai.doubletapp.sample.adaptive.ui.features.main.components.models.ContentType
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationType
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions

const val NOTES_SCREEN = "NotesScreen"

fun NavGraphBuilder.notesScreen(
    contentType: ContentType,
    navigationType: NavigationType
) {
    composable(route = NOTES_SCREEN) {
        NotesScreen(contentType, navigationType)
    }
}

fun NavController.navigateToNotesScreen() = navigate(
    NOTES_SCREEN,
    navOptions {
        launchSingleTop = true
        restoreState = true
        popUpTo(this@navigateToNotesScreen.graph.findStartDestination().id) {
            saveState = true
        }
    }
)
