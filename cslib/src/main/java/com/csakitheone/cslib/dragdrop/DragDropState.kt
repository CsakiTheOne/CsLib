package com.csakitheone.cslib.dragdrop

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Offset

class DragDropState {
    var isDragging by mutableStateOf(false)
    var dragOffset by mutableStateOf(Offset.Zero)

    override fun toString(): String {
        return "${if (isDragging) "✅" else "❌"}${dragOffset.toString().removePrefix("Offset")}"
    }
}