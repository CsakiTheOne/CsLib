package com.csakitheone.cslib.dragdrop

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned

@Composable
fun DropTarget(
    modifier: Modifier = Modifier,
    isDragging: Boolean,
    dragOffset: Offset,
    onDragEnter: () -> Unit,
    onDragExit: () -> Unit = {},
    content: @Composable BoxScope.() -> Unit,
) {
    var isInBounds by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                val rect = it.boundsInWindow()
                if (rect.contains(dragOffset) && !isInBounds && isDragging) {
                    onDragEnter()
                    isInBounds = true
                }
                else if (!rect.contains(dragOffset) && isInBounds) {
                    onDragExit()
                    isInBounds = false
                }
            },
        content = content,
    )
}