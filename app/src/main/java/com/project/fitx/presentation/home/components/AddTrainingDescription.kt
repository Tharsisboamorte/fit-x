package com.project.fitx.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.project.fitx.presentation.auth.components.DefaultButton
import com.project.fitx.ui.theme.PrimaryTheme

@Composable
fun AddDescription(
    text: String,
    onAction: () -> Unit,
    onValueChanged:(String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        OutlinedTextField(
            modifier = Modifier.width(250.dp),
            value = text,
            onValueChange = onValueChanged,
            placeholder = {
                Text(text = "Descrição", color = Color.Gray)
            },
            singleLine = true,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 20.dp),
            horizontalArrangement = Arrangement.End
        ) {
            DefaultButton(
                label = "Adicionar",
                onAction = onAction,
                color = ButtonDefaults.elevatedButtonColors(containerColor = PrimaryTheme),
                textColor = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewAddDescription() {
    Surface {
        AddDescription(onAction = {}, text = "", onValueChanged = {})
    }
}