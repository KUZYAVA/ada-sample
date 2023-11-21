package ai.doubletapp.sample.adaptive.ui.features.settings

import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalNavController
import androidx.compose.runtime.Composable

@Composable
fun SettingsScreen() {
    val navController = LocalNavController.current

    SettingsUi(onBackPressed = navController::navigateUp)
}
