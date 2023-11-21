package ai.doubletapp.sample.adaptive.ui.features.settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val SETTINGS_SCREEN = "SettingsScreen"

fun NavGraphBuilder.settingsScreen() {
    composable(route = SETTINGS_SCREEN) {
        SettingsScreen()
    }
}

fun NavController.navigateToSettingsScreen(navOptions: NavOptions? = null) =
    navigate(SETTINGS_SCREEN, navOptions)
