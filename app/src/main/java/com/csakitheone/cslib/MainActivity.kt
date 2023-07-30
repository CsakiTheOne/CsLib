package com.csakitheone.cslib

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.csakitheone.cslib.dragdrop.Draggable
import com.csakitheone.cslib.dragdrop.DropTarget
import com.csakitheone.cslib.dragdrop.rememberDragDropState
import com.csakitheone.cslib.systemui.SystemBars
import com.csakitheone.cslib.ui.theme.CsLibTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Preview
    @Composable
    fun MainScreen() {
        CsLibTheme {
            var isImmersiveMode by remember { mutableStateOf(false) }
            var dragTarget by remember { mutableStateOf(0) }
            val dragDropState = rememberDragDropState {
                if (dragTarget == 1) isImmersiveMode = !isImmersiveMode
            }

            SystemBars(
                navigationBarColor = MaterialTheme.colorScheme.background,
                isImmersiveMode = isImmersiveMode,
            )

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly,
                ) {
                    Text(text = dragDropState.toString())
                    Draggable(
                        state = dragDropState,
                        isLongPressNeeded = false,
                    ) {
                        Surface(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp),
                            color = MaterialTheme.colorScheme.primary,
                            shadowElevation = 8.dp,
                        ) {}
                    }
                    DropTarget(
                        state = dragDropState,
                        onDragEnter = { dragTarget = 1 },
                        onDragExit = { dragTarget = 0 },
                    ) {
                        Surface(
                            modifier = Modifier
                                .width(100.dp)
                                .height(100.dp),
                            color = if (dragTarget == 1) MaterialTheme.colorScheme.primary
                            else MaterialTheme.colorScheme.surface,
                            shadowElevation = 8.dp,
                        ) {}
                    }
                }
            }
        }
    }
}
