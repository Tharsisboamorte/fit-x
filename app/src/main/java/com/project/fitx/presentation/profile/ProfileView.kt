package com.project.fitx.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.fitx.presentation.auth.AuthViewModel
import com.project.fitx.presentation.auth.components.DefaultButton
import com.project.fitx.ui.theme.PrimaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileView(
    navController: NavController,
    viewModel: AuthViewModel
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Perfil", color = Color.White) }, navigationIcon = {
                IconButton(onClick = { navController.navigate("home") }) {
                    Icon(
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryTheme))
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.AccountCircle,
                contentDescription = "",
                modifier = Modifier.size(95.dp),
                tint = PrimaryTheme
            )
            Text(text = viewModel.currentUser?.displayName!!, fontSize = 35.sp)
            Text(text = viewModel.currentUser!!.email!!, fontSize = 35.sp)
            Spacer(modifier = Modifier.height(55.dp))
            DefaultButton(
                modifier = Modifier
                    .width(290.dp)
                    .height(50.dp),
                label = "Logout",
                onAction = { viewModel.logout() },
                color = ButtonDefaults.elevatedButtonColors(containerColor = Color.Red),
                textColor = Color.White,
            )
            Spacer(modifier = Modifier.height(25.dp))
            DefaultButton(
                modifier = Modifier
                    .width(290.dp)
                    .height(50.dp),
                label = "Delete Account",
                onAction = { viewModel.delete() },
                color = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                textColor = Color.Red,
                borderStroke = BorderStroke(width = 2.dp, color = Color.Red)
            )
        }
    }
}