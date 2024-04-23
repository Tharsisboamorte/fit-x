package com.project.fitx.presentation.profile

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.fitx.presentation.auth.AuthViewModel
import com.project.fitx.presentation.auth.components.DefaultButton
import com.project.fitx.presentation.profile.components.DeleteAccountPopUp
import com.project.fitx.presentation.training.components.PopupBox
import com.project.fitx.ui.theme.PrimaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ProfileView(
    navController: NavController,
    viewModel: AuthViewModel
) {
    var showPopup by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(text = "Perfil", color = Color.White) }, navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
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
            viewModel.currentUser?.let { user ->
                Icon(
                    imageVector = Icons.Outlined.AccountCircle,
                    contentDescription = "",
                    modifier = Modifier.size(95.dp),
                    tint = PrimaryTheme
                )
                Text(text = user.displayName ?: "", fontSize = 35.sp)
                Text(text = user.email ?: "", fontSize = 35.sp)
            }
            Spacer(modifier = Modifier.height(55.dp))
            DefaultButton(
                modifier = Modifier
                    .width(290.dp)
                    .height(50.dp),
                label = "Logout",
                onAction = {
                    viewModel.logout()
                    navController.navigate("login")
                },
                color = ButtonDefaults.elevatedButtonColors(containerColor = Color.Red),
                textColor = Color.White,
            )
            Spacer(modifier = Modifier.height(25.dp))
            DefaultButton(
                modifier = Modifier
                    .width(290.dp)
                    .height(50.dp),
                label = "Delete Account",
                onAction = {
                    showPopup = true
                },
                color = ButtonDefaults.elevatedButtonColors(containerColor = Color.White),
                textColor = Color.Red,
                borderStroke = BorderStroke(width = 2.dp, color = Color.Red)
            )
        }
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Transparent)
    ) {
        PopupBox(
            popupWidth = 250F,
            popupHeight = 300F,
            showPopup = showPopup,
            onClickOutside = { showPopup = false },
            content = {
                DeleteAccountPopUp(
                    onCancel = { showPopup = false },
                    onContinue = {
                        viewModel.delete()
                        navController.navigate("login")
                    })
            }
        )
    }
}