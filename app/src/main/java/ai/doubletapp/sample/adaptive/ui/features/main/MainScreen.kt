package ai.doubletapp.sample.adaptive.ui.features.main

import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalDevicePosture
import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalWindowSizeClass
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.ContentType
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationType
import ai.doubletapp.sample.adaptive.ui.core.adaptive.DevicePosture
import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalNavController
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen() {
    val parentNavController = LocalNavController.current
    val navController = rememberNavController()
    val windowSizeClass = LocalWindowSizeClass.current
    val devicePosture = LocalDevicePosture.current

    val navigationType: NavigationType
    val contentType: ContentType

    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.SINGLE_PANE
        }

        WindowWidthSizeClass.Medium -> {
            navigationType = NavigationType.NAVIGATION_RAIL
            contentType = if (devicePosture != DevicePosture.NormalPosture) {
                ContentType.DUAL_PANE
            } else {
                ContentType.SINGLE_PANE
            }
        }

        WindowWidthSizeClass.Expanded -> {
            navigationType = if (devicePosture is DevicePosture.BookPosture) {
                NavigationType.NAVIGATION_RAIL
            } else {
                NavigationType.PERMANENT_NAVIGATION_DRAWER
            }
            contentType = ContentType.DUAL_PANE
        }

        else -> {
            navigationType = NavigationType.BOTTOM_NAVIGATION
            contentType = ContentType.SINGLE_PANE
        }
    }

    MainUi(
        parentNavController = parentNavController,
        navController = navController,
        navigationType = navigationType
    ) {
        MainGraph(
            navController = navController,
            contentType = contentType,
            navigationType = navigationType
        )
    }
}
