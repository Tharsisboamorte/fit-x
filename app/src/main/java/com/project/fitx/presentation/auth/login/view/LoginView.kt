package com.project.fitx.presentation.auth.login.view

import android.annotation.SuppressLint
import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.project.fitx.data.Resource
import com.project.fitx.data.datasource.auth.AuthRepositoryImpl
import com.project.fitx.presentation.auth.AuthViewModel
import com.project.fitx.presentation.auth.components.DefaultButton
import com.project.fitx.presentation.auth.components.FormTextField
import com.project.fitx.presentation.auth.components.PasswordTextField
import com.project.fitx.ui.theme.PrimaryTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LoginView(
    navController: NavController,
    viewModel: AuthViewModel,
) {
    var textFieldValue by remember {
        mutableStateOf("")
    }
    var passwordTextValue by rememberSaveable { mutableStateOf("") }

    val loginFlow = viewModel.loginFlow.collectAsState()

    var isErrorEmail by rememberSaveable { mutableStateOf(false) }
    var isErrorPassword by rememberSaveable { mutableStateOf(false) }

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
                color = PrimaryTheme,
            )
            Spacer(modifier = Modifier.height(50.dp))
            FormTextField(
                modifier = Modifier.padding(bottom = 15.dp),
                hint = "fulano@gmail.com",
                icon = Icons.Outlined.Face,
                onValueChanged = { emailInput -> textFieldValue = emailInput },
                label = "E-mail",
                text = textFieldValue,
                supportingText = "O email não existe ou foi digitado errado",
                isError = isErrorEmail
            )
            PasswordTextField(
                onValueChanged = { password -> passwordTextValue = password },
                modifier = Modifier.padding(bottom = 25.dp),
                password = passwordTextValue
            )
            Spacer(modifier = Modifier.height(25.dp))
            DefaultButton(
                modifier = Modifier
                    .padding(bottom = 15.dp)
                    .width(280.dp)
                    .height(50.dp),
                label = "Sign in",
                onAction = {
                    viewModel.login(email = textFieldValue, password = passwordTextValue)
                },
                color = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                textColor = PrimaryTheme,
                borderStroke = BorderStroke(2.dp, color = PrimaryTheme)
            )
            DefaultButton(
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp),
                label = "Sign Up",
                onAction = {
                    navController.navigate("register") {
                        popUpTo("register") {
                            inclusive = true
                        }
                    }
                },
                color = ButtonDefaults.elevatedButtonColors(containerColor = PrimaryTheme),
                textColor = Color.White
            )
        }

        loginFlow.value.let {
            when (it) {
                is Resource.Error -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    navController.navigate("home") {
                        popUpTo("home") {
                            inclusive = true
                        }
                    }
                }

                null -> {}
            }
        }
    }
}

@Preview
@Composable
fun PreviewLogin() {
    val navControl = rememberNavController()
    val viewModel = AuthViewModel(repository = AuthRepositoryImpl(FirebaseAuth.getInstance()))
    Surface {
        LoginView(navController = navControl, viewModel = viewModel)
    }
}