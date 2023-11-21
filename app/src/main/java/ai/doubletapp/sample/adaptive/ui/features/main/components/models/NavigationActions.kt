package ai.doubletapp.sample.adaptive.ui.features.main.components.models

import ai.doubletapp.sample.adaptive.ui.features.archive.navigateToArchiveScreen
import ai.doubletapp.sample.adaptive.ui.features.notes.navigateToNotesScreen
import ai.doubletapp.sample.adaptive.ui.features.settings.navigateToSettingsScreen
import ai.doubletapp.sample.adaptive.ui.features.trash.navigateToTrashScreen
import androidx.navigation.NavController

class NavigationActions(
    private val parentNavController: NavController,
    private val navController: NavController,
) {

    fun navigateTo(destination: NavigationDestination) {
        when (destination) {
            NavigationDestination.NOTES -> navController.navigateToNotesScreen()
            NavigationDestination.ARCHIVE -> navController.navigateToArchiveScreen()
            NavigationDestination.TRASH -> navController.navigateToTrashScreen()
            NavigationDestination.SETTINGS -> parentNavController.navigateToSettingsScreen()
        }
    }
}
