package com.project.fitx.presentation.auth.components

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DefaultButton(
    modifier: Modifier = Modifier,
    label: String,
    onAction: () -> Unit,
    color: ButtonColors,
    textColor: Color,
    isEnabled: Boolean = true,
    borderStroke: BorderStroke = BorderStroke(width = 0.dp, color = Color.Transparent)
) {
    ElevatedButton(
        enabled = isEnabled,
        modifier = modifier,
        onClick = onAction,
        shape = RoundedCornerShape(5.dp),
        colors = color,
        border = borderStroke
    ) {
        Text(text = label, color = textColor, fontSize = 15.sp, fontWeight = FontWeight.Bold)
    }
}

@Preview
@Composable
fun PreviewButton() {
    Surface {
        DefaultButton(
            label = "Sign Up",
            onAction = {},
            color = ButtonDefaults.elevatedButtonColors(
                containerColor = Color.Blue,
            ),
            textColor = Color.Green
        )
    }
}