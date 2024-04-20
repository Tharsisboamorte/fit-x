package com.project.fitx.presentation.home.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.project.fitx.presentation.home.components.UserAppBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(

) {
    Scaffold(
        topBar = {
            UserAppBar(name = "Fulano", logOutAction = {}, deleteAction = {})
        }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "HOME PAGE")
        }
    }
}

@Preview
@Composable
fun PreviewHome() {
    Surface {
        HomeView()
    }
}