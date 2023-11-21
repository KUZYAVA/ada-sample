package ai.doubletapp.sample.adaptive.ui.core.setupnavigation

import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalNavController
import ai.doubletapp.sample.adaptive.ui.features.main.MAIN_SCREEN
import ai.doubletapp.sample.adaptive.ui.features.main.mainScreen
import ai.doubletapp.sample.adaptive.ui.features.settings.settingsScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun SetupNavigation() {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = MAIN_SCREEN
        ) {
            mainScreen()
            settingsScreen()
        }
    }
}
