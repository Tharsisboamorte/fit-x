package com.project.fitx.presentation.home.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.project.fitx.presentation.home.components.UserAppBar
import com.project.fitx.ui.theme.PrimaryTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(
    navController: NavController
) {
    Scaffold(
        topBar = {
            UserAppBar(name = "Fulano", logOutAction = {}, deleteAction = {})
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(onClick = { navController.navigate(route = "") }) {
                    Icon(imageVector = Icons.Outlined.Add, contentDescription = "")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 90.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "TREINOS",
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = PrimaryTheme
                )
            }

        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    val navController = rememberNavController()
    Surface {
        HomeView(navController = navController)
    }
}