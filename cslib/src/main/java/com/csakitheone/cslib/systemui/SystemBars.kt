package com.csakitheone.cslib.systemui

import android.app.Activity
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat

/**
 * Composable function to manage the system bar appearance in an Android application.
 *
 * @param statusBarColor The color to be applied to the status bar. If null, the status bar color won't change.
 * @param isStatusBarForegroundLight Determines whether the status bar icons and text should be light or dark. If null, the status bar color won't change.
 * @param navigationBarColor The color to be applied to the navigation bar. If null, the navigation bar color won't change.
 * @param isNavigationBarForegroundLight Determines whether the navigation bar icons and text should be light or dark. If null, the navigation bar color won't change.
 * @param isImmersiveMode If true, status and navigation bars will be hidden. If null, no changes will apply.
 * @param systemBarsBehavior Use WindowInsetsControllerCompat's behavior values.
 */
@Composable
fun SystemBars(
    statusBarColor: Color? = null,
    isStatusBarForegroundLight: Boolean? = null,
    navigationBarColor: Color? = null,
    isNavigationBarForegroundLight: Boolean? = null,
    isImmersiveMode: Boolean? = null,
    systemBarsBehavior: Int? = null,
    onApplyWindowInsets: ((WindowInsetsCompat) -> WindowInsetsCompat)? = null,
) {
    val view = LocalView.current

    LaunchedEffect(Unit) {
        ViewCompat.setOnApplyWindowInsetsListener(view) { _: View, insets: WindowInsetsCompat ->
            onApplyWindowInsets?.invoke(insets) ?: insets
        }
    }

    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val insetsController = WindowCompat.getInsetsController(window, view)

            if (statusBarColor != null) {
                window.statusBarColor = statusBarColor.toArgb()
            }
            if (isStatusBarForegroundLight != null) {
                insetsController.isAppearanceLightStatusBars = !isStatusBarForegroundLight
            }

            if (navigationBarColor != null) {
                window.navigationBarColor = navigationBarColor.toArgb()
            }
            if (isNavigationBarForegroundLight != null) {
                insetsController.isAppearanceLightNavigationBars = !isNavigationBarForegroundLight
            }

            if (isImmersiveMode != null) {
                if (isImmersiveMode) insetsController.hide(WindowInsetsCompat.Type.systemBars())
                else insetsController.show(WindowInsetsCompat.Type.systemBars())
            }

            if (systemBarsBehavior != null) {
                insetsController.systemBarsBehavior = systemBarsBehavior
            }
        }
    }
}