package ai.doubletapp.sample.adaptive.ui.features.main

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val MAIN_SCREEN = "MainScreen"

fun NavGraphBuilder.mainScreen() {
    composable(route = MAIN_SCREEN) {
        MainScreen()
    }
}
