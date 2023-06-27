package com.example.recipeapp.ui.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.recipeapp.R
import com.example.recipeapp.ui.presentation.main.OrderingType
import com.example.recipeapp.ui.theme.Accent

@Composable
fun SelectOrderAlertDialog(
    currSelectedOrder: OrderingType,
    onDismiss: () -> Unit,
    onOrderSelect: (OrderingType) -> Unit
) {
    val selectedOrdering = remember { mutableStateOf(currSelectedOrder) }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.page_info),
                contentDescription = null,
                tint = Accent
            )
        },
        confirmButton = {
            Button(
                onClick = { onOrderSelect(selectedOrdering.value) }
            ) {
                Text(text = stringResource(R.string.confirm))
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text(text = stringResource(R.string.cancel))
            }
        },
        title = {
            Text(text = stringResource(R.string.choose_ordering))
        },
        text = {
            Column {
                enumValues<OrderingType>().forEach { ordering ->
                    OrderingElement(ordering, isSelected = ordering == selectedOrdering.value) { selectedOrdering.value = it }
                }
            }
        }
    )
}