package ai.doubletapp.sample.adaptive.ui.features.main

import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalWindowSizeClass
import ai.doubletapp.sample.adaptive.ui.features.notes.NOTES_SCREEN
import ai.doubletapp.sample.adaptive.ui.features.main.components.BottomNavBar
import ai.doubletapp.sample.adaptive.ui.features.main.components.ModalNavDrawerContent
import ai.doubletapp.sample.adaptive.ui.features.main.components.NavRail
import ai.doubletapp.sample.adaptive.ui.features.main.components.PermanentNavDrawerContent
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationActions
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationDestination
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationContentPosition
import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationType
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowHeightSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import kotlinx.coroutines.launch

@Composable
fun MainUi(
    parentNavController: NavController,
    navController: NavController,
    navigationType: NavigationType,
    content: @Composable () -> Unit
) {
    NavigationWrapper(
        parentNavController = parentNavController,
        navController = navController,
        navigationType = navigationType,
        content = content
    )
}

@Composable
private fun NavigationWrapper(
    parentNavController: NavController,
    navController: NavController,
    navigationType: NavigationType,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navigationActions = remember(parentNavController, navController) {
        NavigationActions(parentNavController, navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination = navBackStackEntry?.destination?.route ?: NOTES_SCREEN

    val windowSizeClass = LocalWindowSizeClass.current
    val navigationContentPosition = when (windowSizeClass.heightSizeClass) {
        WindowHeightSizeClass.Compact -> NavigationContentPosition.TOP

        WindowHeightSizeClass.Medium,
        WindowHeightSizeClass.Expanded -> NavigationContentPosition.CENTER

        else -> NavigationContentPosition.TOP
    }

    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        // TODO check on custom width of PermanentNavigationDrawer: b/232495216
        PermanentNavigationDrawer(drawerContent = {
            PermanentNavDrawerContent(
                selectedDestination = selectedDestination,
                navigationContentPosition = navigationContentPosition,
                navigateToDestination = navigationActions::navigateTo,
            )
        }) {
            MainContent(
                navigationType = navigationType,
                navigationContentPosition = navigationContentPosition,
                selectedDestination = selectedDestination,
                navigateToDestination = navigationActions::navigateTo,
                content = content
            )
        }
    } else {
        ModalNavigationDrawer(
            drawerContent = {
                ModalNavDrawerContent(
                    selectedDestination = selectedDestination,
                    navigationContentPosition = navigationContentPosition,
                    navigateToDestination = navigationActions::navigateTo,
                    onDrawerClicked = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            },
            drawerState = drawerState
        ) {
            MainContent(
                navigationType = navigationType,
                navigationContentPosition = navigationContentPosition,
                selectedDestination = selectedDestination,
                navigateToDestination = navigationActions::navigateTo,
                content = content
            ) {
                scope.launch {
                    drawerState.open()
                }
            }
        }
    }
}

@Composable
fun MainContent(
    navigationType: NavigationType,
    navigationContentPosition: NavigationContentPosition,
    selectedDestination: String,
    navigateToDestination: (NavigationDestination) -> Unit,
    content: @Composable () -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    Row(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(visible = navigationType == NavigationType.NAVIGATION_RAIL) {
            NavRail(
                selectedDestination = selectedDestination,
                navigationContentPosition = navigationContentPosition,
                navigateToDestination = navigateToDestination,
                onDrawerClicked = onDrawerClicked
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                content()
            }
            AnimatedVisibility(visible = navigationType == NavigationType.BOTTOM_NAVIGATION) {
                BottomNavBar(
                    selectedDestination = selectedDestination,
                    navigateToDestination = navigateToDestination
                )
            }
        }
    }
}
