package com.project.fitx.presentation.auth.register.view

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
fun SignUpView(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: AuthViewModel
) {
    var textEmailValue by remember {
        mutableStateOf("")
    }
    var textNameValue by remember {
        mutableStateOf("")
    }
    var passwordTextValue by remember { mutableStateOf("") }

    val registerFlow = viewModel.registerFlow.collectAsState()

    Scaffold(
        containerColor = Color.White
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 15.dp)
        ) {
            Text(
                text = "Create an Account",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                color = PrimaryTheme,
            )
            Spacer(modifier = Modifier.height(50.dp))
            FormTextField(
                modifier = Modifier.padding(bottom = 15.dp),
                hint = "fulano",
                icon = Icons.Outlined.Face,
                label = "Name",
                text = textNameValue,
                onValueChanged = { newName ->
                    textNameValue = newName
                }

            )
            FormTextField(
                modifier = Modifier.padding(bottom = 15.dp),
                hint = "fulano@gmail.com",
                icon = Icons.Outlined.Face,
                text = textEmailValue,
                label = "E-mail",
                onValueChanged = { newValue -> textEmailValue = newValue }
            )
            PasswordTextField(
                modifier = Modifier.padding(bottom = 25.dp),
                onValueChanged = { newPassword ->
                    passwordTextValue = newPassword
                },
                password = passwordTextValue
            )
            Spacer(modifier = Modifier.height(25.dp))
            DefaultButton(
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp),
                label = "Register",
                onAction = {
                    viewModel.signup(
                        email = textEmailValue,
                        password = passwordTextValue,
                        name = textNameValue,
                    )
                },
                color = ButtonDefaults.elevatedButtonColors(containerColor = PrimaryTheme),
                textColor = Color.White
            )
            Spacer(modifier = Modifier.height(15.dp))
            DefaultButton(
                modifier = Modifier
                    .width(280.dp)
                    .height(50.dp),
                label = "Cancelar",
                onAction = {
                    navController.popBackStack()
                },
                color = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                textColor = PrimaryTheme,
                borderStroke = BorderStroke(width = 3.dp, color = PrimaryTheme)
            )
        }

        registerFlow.value.let {
            when (it) {
                is Resource.Error -> {
                    val context = LocalContext.current
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }

                is Resource.Success -> {
                    navController.navigate("login") {
                        popUpTo("login") {
                            inclusive = true
                        }
                    }
                }

                null -> {

                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewSignUp() {
    val navController = rememberNavController()
    val viewModel = AuthViewModel(repository = AuthRepositoryImpl(FirebaseAuth.getInstance()))
    Surface {
        SignUpView(navController = navController, viewModel = viewModel)
    }
}