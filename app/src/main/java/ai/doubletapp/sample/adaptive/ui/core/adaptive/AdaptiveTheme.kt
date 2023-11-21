package ai.doubletapp.sample.adaptive.ui.core.adaptive

import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalDevicePosture
import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalDisplayFeatures
import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalWindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature

@Composable
fun AdaptiveTheme(
    windowSizeClass: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    content: @Composable () -> Unit
) {
    val foldingFeature = displayFeatures.filterIsInstance<FoldingFeature>().firstOrNull()
    val devicePosture = when {
        isBookPosture(foldingFeature) ->
            DevicePosture.BookPosture(foldingFeature.bounds)

        isSeparating(foldingFeature) ->
            DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

        else -> DevicePosture.NormalPosture
    }

    CompositionLocalProvider(
        LocalWindowSizeClass provides windowSizeClass,
        LocalDisplayFeatures provides displayFeatures,
        LocalDevicePosture provides devicePosture,
        content = content
    )
}
