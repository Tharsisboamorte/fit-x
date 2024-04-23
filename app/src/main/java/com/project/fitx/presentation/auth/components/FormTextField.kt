package com.project.fitx.presentation.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    hint: String,
    icon: ImageVector,
    text: String,
    label: String,
    isError: Boolean = false,
    onValueChanged: (String) -> Unit,
    supportingText: String = ""
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = "$label *",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        OutlinedTextField(
            value = text,
            isError = isError,
            onValueChange = onValueChanged,
            placeholder = {
                Text(text = hint, color = Color.Gray)
            },
            leadingIcon = {
                Icon(imageVector = icon, contentDescription = "")
            },
            singleLine = true,
            supportingText = {
                if (isError) {
                    Text(
                        text = supportingText,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        )
    }
}