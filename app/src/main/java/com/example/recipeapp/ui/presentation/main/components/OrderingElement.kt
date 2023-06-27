package com.example.recipeapp.ui.presentation.main.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.recipeapp.ui.presentation.main.OrderingType
import com.example.recipeapp.ui.theme.Accent
import com.example.recipeapp.ui.theme.Transparent

@Composable
fun OrderingElement(
    element: OrderingType,
    isSelected: Boolean = false,
    onClick: (OrderingType) -> Unit
) {
    val color = if (isSelected) Accent else Transparent
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(RoundedCornerShape(16.dp))
            .border(2.dp, color, RoundedCornerShape(16.dp))
            .clickable { onClick(element) }
            .padding(vertical = 4.dp)
    ) {
        Text(
            text = stringResource(element.string),
            modifier = Modifier.padding(horizontal = 8.dp)
        )
    }
}