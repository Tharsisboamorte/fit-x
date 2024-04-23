package com.project.fitx.presentation.home.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.project.fitx.presentation.home.HomeViewModel
import com.project.fitx.presentation.home.components.AddDescription
import com.project.fitx.presentation.home.components.TrainingCard
import com.project.fitx.presentation.home.components.UserAppBar
import com.project.fitx.ui.theme.PrimaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeView(
    navController: NavController,
    viewModel: HomeViewModel,
) {
    var showPopup by rememberSaveable { mutableStateOf(false) }

    var textFieldValue by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            UserAppBar(
                name = viewModel.currentUser?.displayName!!,
                onAction = {
                    navController.navigate("profile")
                })
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(color = Color.Transparent),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                FloatingActionButton(
                    onClick = {
                        showPopup = true
                    },
                    containerColor = PrimaryTheme
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Add,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
            }
        }
    ) {
        if (showPopup) {
            AlertDialog(
                onDismissRequest = { showPopup = false },
                modifier = Modifier.background(color = Color.White)
            ) {
                AddDescription(
                    text = textFieldValue,
                    onAction = {
                        viewModel.createTraining(textFieldValue)
                        showPopup = false
                        viewModel.reset()
                    },
                    onValueChanged = { newText ->
                        textFieldValue = newText
                    }
                )
            }
        }

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
            LazyColumn(
                userScrollEnabled = true,
                modifier = Modifier.padding(start = 15.dp, top = 25.dp, end = 15.dp)
            ) {
                items(viewModel.trainingList) { item ->
                    TrainingCard(description = item.descricao, date = item.data.toString()) {
                        navController.navigate("details?title=${item.descricao}") {
                            launchSingleTop = true
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }

        }
    }
}