package com.example.recipeapp.ui.presentation.main.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.recipeapp.ui.theme.White

@Composable
fun RecipeCard(
    title: String,
    description: String,
    image: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp, horizontal = 16.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Row {
           AsyncImage(
               model = image,
               contentDescription = null,
               modifier = Modifier
                   .height(110.dp)
                   .width(100.dp),
               contentScale = ContentScale.Crop,
           )
           Column(
               modifier = Modifier
                   .padding(top = 16.dp, start = 8.dp, end = 16.dp, bottom = 16.dp)
                   .weight(2f)
           ) {
               Text(
                   text = title,
                   modifier = Modifier
                       .padding(bottom = 4.dp),
                   fontWeight = FontWeight.Medium,
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )
               Text(
                   text = description,
                   modifier = Modifier
                       .padding(),
                   fontWeight = FontWeight.Normal,
                   maxLines = 2,
                   overflow = TextOverflow.Ellipsis
               )
           }
        }
    }
}