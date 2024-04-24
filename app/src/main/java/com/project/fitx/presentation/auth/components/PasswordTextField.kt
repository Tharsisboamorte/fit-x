package com.project.fitx.presentation.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.fitx.R

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    password: String,
    supportedText: String = "",
    isError: Boolean = false,
) {
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
    ) {
        Text(
            text = "Password *",
            fontSize = 16.sp,
            modifier = Modifier.padding(bottom = 5.dp)
        )
        OutlinedTextField(
            value = password,
            onValueChange = onValueChanged,
            placeholder = {
                Text(text = "Senha", color = Color.Gray)
            },
            leadingIcon = {
                Icon(imageVector = Icons.Outlined.Lock, contentDescription = "")
            },
            isError = isError,
            supportingText = { Text(text = supportedText) },
            singleLine = true,
            visualTransformation = if (passwordVisible)
                VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val image =
                    if (passwordVisible) R.drawable.visibility_on else R.drawable.visibility_off

                IconButton(
                    onClick = { passwordVisible = !passwordVisible },
                    modifier = Modifier.size(25.dp)
                ) {
                    Icon(painter = painterResource(id = image), contentDescription = "")
                }
            }
        )
        Text(
            text = "Digite minimo de 6 digitos.",
            fontSize = 12.sp,
            color = Color.Gray
        )
    }
}