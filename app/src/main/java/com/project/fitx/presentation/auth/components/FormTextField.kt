package com.project.fitx.presentation.auth.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    hint: String,
    icon: ImageVector,
    textValue: String,
    label: String,
    isRequired: Boolean,
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(text = textValue))
    }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "$label ${if (isRequired) "*" else ""}",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 5.dp)
            )
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { newValue ->
                textFieldValue = newValue
            },
            placeholder = {
                Text(text = hint, color = Color.Gray)
            },
            leadingIcon = {
                Icon(imageVector = icon, contentDescription = "")
            },
            singleLine = true,
        )
    }
}


@Preview
@Composable
fun PreviewTextField() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            FormTextField(
                hint = "Fulano",
                icon = Icons.Default.Face,
                textValue = "",
                label = "Name",
                isRequired = true
            )
        }
    }
}