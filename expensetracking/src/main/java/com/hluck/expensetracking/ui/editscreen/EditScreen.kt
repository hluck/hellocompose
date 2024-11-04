package com.hluck.expensetracking.ui.editscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.hluck.expensetracking.ui.viewmodels.EditViewModel

@Composable
fun EditScreen(
    appNavController: NavController,
    viewModel: EditViewModel
) {
    Scaffold(
        topBar = {
            EditTopBar(
                appNavController,
                viewModel
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            if (!viewModel.showIncome) {
                EditPaidGridIconComponent(
                    viewModel = viewModel,
                    modifier = Modifier.weight(1f)
                )
            } else {
                IncomeIconComponent(
                    viewModel = viewModel,
                    modifier = Modifier.weight(1f)
                )
            }
            BottomComponent(
                viewModel = viewModel,
                modifier = Modifier.weight(1f)
            )
        }
    }
}