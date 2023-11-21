package ai.doubletapp.sample.adaptive.ui.features.archive

import ai.doubletapp.sample.adaptive.ui.core.empty.EmptyScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions

const val ARCHIVE_SCREEN = "ArchiveScreen"

fun NavGraphBuilder.archiveScreen() {
    composable(route = ARCHIVE_SCREEN) {
        EmptyScreen(text = ARCHIVE_SCREEN)
    }
}

fun NavController.navigateToArchiveScreen() = navigate(
    ARCHIVE_SCREEN,
    navOptions {
        launchSingleTop = true
        restoreState = true
        popUpTo(this@navigateToArchiveScreen.graph.findStartDestination().id) {
            saveState = true
        }
    }
)
