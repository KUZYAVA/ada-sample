package ai.doubletapp.sample.adaptive.ui.core.localproviders

import ai.doubletapp.sample.adaptive.ui.core.adaptive.DevicePosture
import androidx.activity.ComponentActivity
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController
import androidx.window.layout.DisplayFeature

val LocalActivity = staticCompositionLocalOf<ComponentActivity> {
    error("No LocalActivity provided")
}

val LocalNavController = compositionLocalOf<NavHostController> {
    error("No LocalNavController provided")
}

val LocalWindowSizeClass = compositionLocalOf<WindowSizeClass> {
    error("No LocalWindowSizeClass provided")
}

val LocalDisplayFeatures = compositionLocalOf<List<DisplayFeature>> {
    error("No DisplayFeatures provided")
}

val LocalDevicePosture = compositionLocalOf<DevicePosture> {
    error("No FoldingDevicePosture provided")
}
