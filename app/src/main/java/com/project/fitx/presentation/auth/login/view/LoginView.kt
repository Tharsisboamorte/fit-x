package com.project.fitx.presentation.auth.login.view

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.fitx.presentation.auth.components.DefaultButton
import com.project.fitx.presentation.auth.components.FormTextField
import com.project.fitx.presentation.auth.components.PasswordTextField
import com.project.fitx.ui.theme.Purple40

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(
    navController: NavController,
) {
    var textFieldValue by remember {
        mutableStateOf(TextFieldValue(""))
    }
    var passwordTextValue by rememberSaveable { mutableStateOf("") }

    Scaffold(
        containerColor = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "Sign in",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = Purple40,
            )
            Spacer(modifier = Modifier.height(50.dp))
            FormTextField(
                modifier = Modifier.padding(bottom = 15.dp),
                hint = "fulano@gmail.com",
                icon = Icons.Outlined.Face,
                textValue = textFieldValue.text,
                label = "E-mail",
                isRequired = true
            )
            PasswordTextField(
                textValue = passwordTextValue,
                modifier = Modifier.padding(bottom = 25.dp)
            )
            Spacer(modifier = Modifier.height(25.dp))
            DefaultButton(
                modifier = Modifier
                    .padding(15.dp)
                    .width(280.dp)
                    .height(50.dp),
                label = "Sign in",
                onAction = { /*TODO*/ },
                color = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                textColor = Purple40,
                borderStroke = BorderStroke(2.dp, color = Purple40)
            )
            DefaultButton(
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp),
                label = "Sign Up",
                onAction = { navController.navigate("register") },
                color = ButtonDefaults.elevatedButtonColors(containerColor = Purple40),
                textColor = Color.White
            )
        }
    }
}

@Preview
@Composable
fun PreviewLogin() {
    val navControl = rememberNavController()
    Surface {
        LoginView(navController = navControl)
    }
}