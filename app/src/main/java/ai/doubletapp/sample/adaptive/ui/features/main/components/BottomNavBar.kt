package ai.doubletapp.sample.adaptive.ui.features.main.components

import ai.doubletapp.sample.adaptive.ui.features.main.components.models.NavigationDestination
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun BottomNavBar(
    selectedDestination: String,
    navigateToDestination: (NavigationDestination) -> Unit
) {
    NavigationBar(modifier = Modifier.fillMaxWidth()) {
        NavigationDestination.values().forEach { destination ->
            NavigationBarItem(
                selected = selectedDestination == destination.route,
                onClick = { navigateToDestination(destination) },
                icon = {
                    Icon(
                        imageVector = destination.selectedIcon,
                        contentDescription = stringResource(id = destination.iconTextId)
                    )
                }
            )
        }
    }
}
