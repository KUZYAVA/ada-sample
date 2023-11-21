package ai.doubletapp.sample.adaptive.ui.features.trash

import ai.doubletapp.sample.adaptive.ui.core.empty.EmptyScreen
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navOptions

const val TRASH_SCREEN = "TrashScreen"

fun NavGraphBuilder.trashScreen() {
    composable(route = TRASH_SCREEN) {
        EmptyScreen(text = TRASH_SCREEN)
    }
}

fun NavController.navigateToTrashScreen() = navigate(
    TRASH_SCREEN,
    navOptions {
        launchSingleTop = true
        restoreState = true
        popUpTo(this@navigateToTrashScreen.graph.findStartDestination().id) {
            saveState = true
        }
    }
)
