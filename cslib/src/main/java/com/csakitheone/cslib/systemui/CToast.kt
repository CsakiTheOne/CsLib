package com.csakitheone.cslib.systemui

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext

/**
 * Displays a toast message in the current composition.
 * The toast will display every time one of the parameters change and the text is not blank.
 *
 * @param text The text to be displayed in the toast message.
 * @param duration How long to display the message. Either Toast.LENGTH_SHORT (default) or
 *                 Toast.LENGTH_LONG.
 */
@Composable
fun CToast(
    text: String,
    duration: Int = Toast.LENGTH_SHORT,
) {
    val context = LocalContext.current

    SideEffect {
        if (text.isNotBlank()) Toast.makeText(context, text, duration).show()
    }
}