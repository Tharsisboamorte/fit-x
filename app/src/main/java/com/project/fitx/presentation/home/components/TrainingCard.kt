package com.project.fitx.presentation.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.fitx.ui.theme.PrimaryTheme
import com.project.fitx.utils.formatDate
import java.time.LocalDate

@Composable
fun TrainingCard(
    description: String,
    date: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth(),
        border = BorderStroke(width = 2.dp, color = PrimaryTheme),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 15.dp, top = 15.dp, bottom = 15.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(300.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = description,
                    modifier = Modifier.padding(start = 5.dp),
                    color = PrimaryTheme,
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    text = "Criado: ${formatDate(date)}",
                    modifier = Modifier.padding(start = 5.dp),
                    color = PrimaryTheme
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onClick,
                    colors = IconButtonDefaults.filledIconButtonColors(
                        containerColor = PrimaryTheme,
                        contentColor = Color.White
                    )
                ) {
                    Icon(imageVector = Icons.Outlined.ArrowForward, contentDescription = "")
                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewCard() {
    Surface {
        TrainingCard(
            date = LocalDate.now().toString(),
            description = "Treino de teste sem pressa",
            onClick = {}
        )
    }
}
