package com.project.fitx.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.fitx.ui.theme.PrimaryTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserAppBar(
    name: String,
    logOutAction: () -> Unit,
    deleteAction: () -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()
    var toggleVisibility by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)),
        color = PrimaryTheme,
        shadowElevation = 15.dp,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(
                text = "Bem-vindo!\n$name",
                modifier = Modifier.padding(start = 9.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(
                    onClick = {
                        coroutineScope.launch {
                            toggleVisibility = !toggleVisibility
                        }
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "")
                }
            }
            DropdownMenu(
                expanded = toggleVisibility,
                onDismissRequest = { toggleVisibility = false },
                modifier = Modifier.align(alignment = Alignment.CenterVertically)
            ) {
                DropdownMenuItem(text = { Text(text = "LogOut") }, onClick = logOutAction)
                DropdownMenuItem(text = { Text(text = "Delete Account") }, onClick = deleteAction)
            }
        }
    }
}

@Preview
@Composable
fun PreviewUserBar() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            UserAppBar(
                name = "Fulano",
                logOutAction = {},
                deleteAction = {}
            )
        }
    }
}