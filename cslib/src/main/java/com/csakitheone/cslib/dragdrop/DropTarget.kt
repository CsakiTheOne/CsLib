package com.csakitheone.cslib.dragdrop

import android.util.Log
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
fun DropTarget(
    modifier: Modifier = Modifier,
    state: DragDropState,
    onDragEnter: () -> Unit,
    onDragExit: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    var rect by remember { mutableStateOf(Rect.Zero) }
    var isInBounds by remember { mutableStateOf(false) }

    LaunchedEffect(state.dragOffset, rect) {
        if (rect.contains(state.dragOffset) && !isInBounds && state.isDragging) {
            onDragEnter()
            isInBounds = true
        }
        else if (!rect.contains(state.dragOffset) && isInBounds) {
            onDragExit()
            isInBounds = false
        }
    }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                rect = it.boundsInWindow()
            },
        content = content,
    )
}