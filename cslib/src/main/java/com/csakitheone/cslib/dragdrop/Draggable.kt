package com.csakitheone.cslib.dragdrop

import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlin.math.roundToInt

@Composable
fun Draggable(
    modifier: Modifier = Modifier,
    state: DragDropState,
    isLongPressNeeded: Boolean = true,
    showGhost: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    var currentPosition by remember { mutableStateOf(Offset.Zero) }

    Box(
        modifier = modifier
            .onGloballyPositioned {
                currentPosition = it.localToWindow(Offset.Zero)
            }
            .pointerInput(Unit) {
                if (isLongPressNeeded) {
                    detectDragGesturesAfterLongPress(
                        onDragStart = {
                            state.isDragging = true
                            state.dragOffset = currentPosition + it
                        }, onDrag = { change, dragAmount ->
                            change.consume()
                            state.dragOffset += dragAmount
                        }, onDragEnd = {
                            state.isDragging = false
                            state.dragEnd()
                            state.dragOffset = Offset.Zero
                        }, onDragCancel = {
                            state.isDragging = false
                            state.dragOffset = Offset.Zero
                        }
                    )
                } else {
                    detectDragGestures(
                        onDragStart = {
                            state.isDragging = true
                            state.dragOffset = currentPosition + it
                        }, onDrag = { change, dragAmount ->
                            change.consume()
                            state.dragOffset += dragAmount
                        }, onDragEnd = {
                            state.isDragging = false
                            state.dragEnd()
                            state.dragOffset = Offset.Zero
                        }, onDragCancel = {
                            state.isDragging = false
                            state.dragOffset = Offset.Zero
                        }
                    )
                }
            },
    ) {
        if (showGhost && state.isDragging) {
            Popup(
                offset = IntOffset(
                    (state.dragOffset.x - currentPosition.x).roundToInt(),
                    (state.dragOffset.y - currentPosition.y).roundToInt()
                ),
            ) {
                Box {
                    content()
                }
            }
        }
        content()
    }
}