package com.project.fitx.presentation.training.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.fitx.ui.theme.PrimaryTheme

@Composable
fun TrainingTopBar(
    onBackAction: () -> Unit,
    title: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .background(color = PrimaryTheme),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(onClick = onBackAction) {
            Icon(
                imageVector = Icons.Outlined.ArrowBack,
                contentDescription = "",
                tint = Color.White
            )
        }
        Text(
            text = title,
            color = Color.White,
            fontSize = 25.sp,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
        )

    }
}

@Preview
@Composable
fun PreviewTopBar() {
    Surface {
        TrainingTopBar(onBackAction = {}, title = "TITLE")
    }
}