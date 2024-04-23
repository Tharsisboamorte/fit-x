package com.project.fitx.presentation.training.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.project.fitx.ui.theme.PrimaryTheme

@Composable
fun PopupBox(
    popupWidth: Float,
    popupHeight: Float,
    showPopup: Boolean,
    onClickOutside: () -> Unit,
    content: @Composable() () -> Unit
) {

    if (showPopup) {

        Card(
            modifier = Modifier
                .wrapContentSize()
                .background(color = Color.Transparent),
            shape = RoundedCornerShape(15.dp),
            border = BorderStroke(width = 3.dp, color = PrimaryTheme)
        ) {
            // popup
            Popup(
                alignment = Alignment.Center,
                properties = PopupProperties(
                    excludeFromSystemGesture = true,
                ),
                // to dismiss on click outside
                onDismissRequest = { onClickOutside() }
            ) {
                Box(
                    Modifier
                        .width(popupWidth.dp)
                        .height(popupHeight.dp)
                        .background(color = Color.White)
                        .border(border = BorderStroke(width = 5.dp, color = PrimaryTheme)),
                    contentAlignment = Alignment.Center
                ) {
                    content()
                }
            }
        }
    }
}