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
    onDragStateChange: (isDragging: Boolean, offset: Offset) -> Unit,
    isLongPressNeeded: Boolean = true,
    showGhost: Boolean = true,
    content: @Composable BoxScope.() -> Unit,
) {
    var isDragging by remember { mutableStateOf(false) }
    var localOffset by remember { mutableStateOf(Offset.Zero) }
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
                            isDragging = true
                            localOffset = currentPosition + it
                            onDragStateChange(isDragging, localOffset)
                        }, onDrag = { change, dragAmount ->
                            change.consume()
                            localOffset += dragAmount
                            onDragStateChange(isDragging, localOffset)
                        }, onDragEnd = {
                            isDragging = false
                            onDragStateChange(isDragging, Offset.Zero)
                        }, onDragCancel = {
                            isDragging = false
                            onDragStateChange(isDragging, Offset.Zero)
                        }
                    )
                } else {
                    detectDragGestures(
                        onDragStart = {
                            isDragging = true
                            localOffset = currentPosition + it
                            onDragStateChange(isDragging, localOffset)
                        }, onDrag = { change, dragAmount ->
                            change.consume()
                            localOffset += dragAmount
                            onDragStateChange(isDragging, localOffset)
                        }, onDragEnd = {
                            isDragging = false
                            onDragStateChange(isDragging, Offset.Zero)
                        }, onDragCancel = {
                            isDragging = false
                            onDragStateChange(isDragging, Offset.Zero)
                        }
                    )
                }
            },
    ) {
        if (showGhost && isDragging) {
            Popup(
                offset = IntOffset(
                    (localOffset.x - currentPosition.x).roundToInt(),
                    (localOffset.y - currentPosition.y).roundToInt()
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