package com.project.fitx.presentation.training.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.fitx.presentation.home.HomeViewModel
import com.project.fitx.presentation.home.components.AddDescription
import com.project.fitx.presentation.training.TrainingViewModel
import com.project.fitx.presentation.training.components.ExerciseItem
import com.project.fitx.presentation.training.components.TrainingTopBar
import com.project.fitx.ui.theme.PrimaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrainingView(
    navController: NavController,
    trainingViewModel: TrainingViewModel,
    homeViewModel: HomeViewModel,
    title: String,
    trainingId: String
) {

    var showPopup by rememberSaveable { mutableStateOf(false) }

    var textFieldValue by remember {
        mutableStateOf("")
    }

    Scaffold(
        topBar = {
            TrainingTopBar(
                onBackAction = { navController.popBackStack() },
                title = title,
                onDelete = {
                    trainingViewModel.trainingDelete(trainingId = trainingId)
                    homeViewModel.reset()
                    navController.navigate("loading")
                },
                onEdit = {
                    showPopup = true
                }
            )
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
                    onClick = {},
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
                        trainingViewModel.trainingEdit(
                            newTitle = textFieldValue,
                            trainingId = trainingId
                        )
                        showPopup = false
                        homeViewModel.reset()
                        navController.navigate("loading")
                    },
                    onValueChanged = { newText ->
                        textFieldValue = newText
                    }
                )
            }
        }

        LazyColumn(
            modifier = Modifier.padding(top = 105.dp)
        ) {
            items(trainingViewModel.exercisesList) { item ->
                ExerciseItem(image = item.img.toString(), title = item.observations)
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}