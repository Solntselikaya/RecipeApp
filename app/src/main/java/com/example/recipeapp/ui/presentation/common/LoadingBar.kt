package com.example.recipeapp.ui.presentation.common

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.recipeapp.ui.theme.Accent

@Composable
fun LoadingBar(
    modifier: Modifier = Modifier,
    color: Color = Accent
) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center),
            color = color
        )
    }
}