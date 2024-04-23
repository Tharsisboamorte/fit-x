package com.project.fitx.presentation.training.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.project.fitx.ui.theme.PrimaryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingTopBar(
    onDelete: () -> Unit,
    onEdit: () -> Unit,
    onBackAction: () -> Unit,
    title: String
) {
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .height(100.dp)
//            .background(color = PrimaryTheme),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        IconButton(onClick = onBackAction) {
//            Icon(
//                imageVector = Icons.Outlined.ArrowBack,
//                contentDescription = "",
//                tint = Color.White
//            )
//        }
//        Text(
//            text = title,
//            color = Color.White,
//            fontSize = 25.sp,
//            overflow = TextOverflow.Ellipsis,
//            softWrap = true,
//        )
//
//    }
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = PrimaryTheme),
        title = {
            Text(
                text = title,
                color = Color.White,
                fontSize = 25.sp,
                overflow = TextOverflow.Ellipsis,
                softWrap = true,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackAction) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        actions = {
            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = "",
                    tint = Color.White
                )
            }
            IconButton(onClick = onEdit) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewTopBar() {
    Surface {
        TrainingTopBar(onBackAction = {}, title = "TITLE", onDelete = {}, onEdit = {})
    }
}