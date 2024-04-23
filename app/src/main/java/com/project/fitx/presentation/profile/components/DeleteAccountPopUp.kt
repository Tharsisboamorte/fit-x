package com.project.fitx.presentation.profile.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.fitx.presentation.auth.components.DefaultButton
import com.project.fitx.ui.theme.PrimaryTheme

@Composable
fun DeleteAccountPopUp(
    onCancel: () -> Unit,
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Tem certeza que deseja\n deletar sua conta?",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = PrimaryTheme
        )
        Spacer(modifier = Modifier.height(25.dp))
        DefaultButton(
            modifier = Modifier.width(120.dp),
            label = "Sim",
            onAction = onContinue,
            color = ButtonDefaults.elevatedButtonColors(containerColor = Color.Red),
            textColor = Color.White
        )
        DefaultButton(
            modifier = Modifier.width(120.dp),
            label = "Cancelar",
            onAction = onCancel,
            color = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
            textColor = PrimaryTheme,
            borderStroke = BorderStroke(width = 2.dp, color = PrimaryTheme)
        )

    }
}


@Preview
@Composable
fun PreviewDeletePop() {
    Surface {
        DeleteAccountPopUp(onCancel = {}, onContinue = {})
    }
}