package ai.doubletapp.sample.adaptive.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ai.doubletapp.sample.adaptive.ui.core.localproviders.LocalActivity
import ai.doubletapp.sample.adaptive.ui.core.setupnavigation.SetupNavigation
import ai.doubletapp.sample.adaptive.ui.theme.DTNotesTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.google.accompanist.adaptive.calculateDisplayFeatures

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DTNotesTheme(
                windowSizeClass = calculateWindowSizeClass(this),
                displayFeatures = calculateDisplayFeatures(this)
            ) {
                CompositionLocalProvider(LocalActivity provides this) {
                    SetupNavigation()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DTNotesPreview() {
    DTNotesTheme {
        SetupNavigation()
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 700, heightDp = 500)
@Composable
fun DTNotesPreviewTablet() {
    DTNotesTheme(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(700.dp, 500.dp))
    ) {
        SetupNavigation()
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 500, heightDp = 700)
@Composable
fun DTNotesPreviewTabletPortrait() {
    DTNotesTheme(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(500.dp, 700.dp))
    ) {
        SetupNavigation()
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true, widthDp = 1100, heightDp = 600)
@Composable
fun DTNotesPreviewDesktop() {
    DTNotesTheme(
        windowSizeClass = WindowSizeClass.calculateFromSize(DpSize(1100.dp, 600.dp))
    ) {
        SetupNavigation()
    }
}
