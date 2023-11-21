package ai.doubletapp.sample.adaptive.ui.features.settings

import ai.doubletapp.sample.adaptive.ui.core.empty.EmptyScreen
import ai.doubletapp.sample.adaptive.ui.core.topbar.TopBar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SettingsUi(
    onBackPressed: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inverseOnSurface)
            .padding(top = 16.dp)
    ) {
        TopBar(onBackPressed = onBackPressed)
        EmptyScreen(text = SETTINGS_SCREEN)
    }
}
