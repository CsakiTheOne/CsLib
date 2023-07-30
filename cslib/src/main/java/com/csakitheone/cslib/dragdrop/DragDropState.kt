package com.csakitheone.cslib.dragdrop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

class DragDropState(
    private val onDragEnded: () -> Unit,
) {
    var isDragging by mutableStateOf(false)
    var dragOffset by mutableStateOf(Offset.Zero)

    fun dragEnd() {
        onDragEnded()
    }

    override fun toString(): String {
        return "${if (isDragging) "✅" else "❌"}${dragOffset.toString().removePrefix("Offset")}"
    }
}