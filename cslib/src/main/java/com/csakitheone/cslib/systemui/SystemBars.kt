package com.csakitheone.cslib.systemui

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

/**
 * Composable function to manage the system bar appearance in an Android application.
 *
 * @param statusBarColor The color to be applied to the status bar. If null, the status bar color won't change.
 * @param isStatusBarForegroundLight Determines whether the status bar icons and text should be light or dark. If null, the status bar color won't change.
 * @param navigationBarColor The color to be applied to the navigation bar. If null, the navigation bar color won't change.
 * @param isNavigationBarForegroundLight Determines whether the navigation bar icons and text should be light or dark. If null, the navigation bar color won't change.
 */
@Composable
fun SystemBars(
    statusBarColor: Color? = null,
    isStatusBarForegroundLight: Boolean? = null,
    navigationBarColor: Color? = null,
    isNavigationBarForegroundLight: Boolean? = null,
) {
    val view = LocalView.current

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
        }
    }
}