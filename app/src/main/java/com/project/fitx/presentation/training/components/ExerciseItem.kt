package com.project.fitx.presentation.training.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.project.fitx.ui.theme.PrimaryTheme

@Composable
fun ExerciseItem(
    image: String,
    title: String,
) {
    Row(
        modifier = Modifier
            .padding(start = 5.dp, end = 25.dp)
            .fillMaxWidth()
            .height(190.dp)
            .border(
                border = BorderStroke(width = 3.dp, color = PrimaryTheme),
                shape = RoundedCornerShape(5.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Spacer(modifier = Modifier.width(15.dp))
        Card {
            AsyncImage(
                model = image,
                contentDescription = "",
                modifier = Modifier
                    .size(155.dp),
            )
        }
        Text(text = title, modifier = Modifier.padding(start = 15.dp))
    }
}

@Preview
@Composable
fun PreviewItem() {
    Surface {
        ExerciseItem(
            image = "",
            title = "Nestros basaquiota vivi nas hi"
        )
    }
}