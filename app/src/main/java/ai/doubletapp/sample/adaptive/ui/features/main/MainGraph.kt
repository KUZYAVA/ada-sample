package ai.doubletapp.sample.adaptive.ui.features.main

import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalNavController
import ai.doubletapp.sample.adaptive.ui.features.archive.archiveScreen
import ai.doubletapp.sample.adaptive.ui.features.trash.trashScreen
import ai.doubletapp.sample.adaptive.ui.features.notes.NOTES_SCREEN
import ai.doubletapp.sample.adaptive.ui.features.notes.notesScreen
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.ContentType
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainGraph(
    navController: NavHostController,
    contentType: ContentType,
    navigationType: NavigationType
) {
    CompositionLocalProvider(
        LocalNavController provides navController
    ) {
        NavHost(
            navController = navController,
            startDestination = NOTES_SCREEN
        ) {
            notesScreen(contentType, navigationType)
            archiveScreen()
            trashScreen()
        }
    }
}
