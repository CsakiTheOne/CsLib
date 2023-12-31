package com.csakitheone.cslib.dragdrop

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
fun rememberDragDropState(
    onDragEnded: () -> Unit,
): DragDropState {
    return remember {
        DragDropState(
            onDragEnded = onDragEnded,
        )
    }
}