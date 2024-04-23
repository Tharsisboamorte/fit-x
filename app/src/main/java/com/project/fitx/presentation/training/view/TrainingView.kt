package com.project.fitx.presentation.training.view

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.fitx.presentation.training.TrainingViewModel
import com.project.fitx.presentation.training.components.ExerciseList
import com.project.fitx.presentation.training.components.PopupBox
import com.project.fitx.presentation.training.components.TrainingTopBar
import com.project.fitx.ui.theme.PrimaryTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun TrainingView(
    navController: NavController,
    trainingViewModel: TrainingViewModel,
) {
    var showPopup by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TrainingTopBar(
                onBackAction = { navController.popBackStack() },
                title = "TrainingTitle",
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
                    onClick = {
                        showPopup = true
                        trainingViewModel.getExercises()
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

    }
    PopupBox(
        popupWidth = 200F,
        popupHeight = 300F,
        showPopup = showPopup,
        onClickOutside = { showPopup = false },
        content = {
            ExerciseList(exercises = trainingViewModel.exercisesList)
        }
    )
}