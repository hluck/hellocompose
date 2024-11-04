package com.hluck.expensetracking.ui.homescreen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hluck.expensetracking.ui.components.AppTopBarComponent
import com.hluck.expensetracking.ui.editscreen.ExpenseItem

@Composable
fun ExpenseScreen(
    appNavController: NavController
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            AppTopBarComponent()
        }
        item {
            MonthCard()
        }
        item {
            ExpenseItem(appNavController)
        }
    }
}