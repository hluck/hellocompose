package com.hluck.expensetracking.ui.editscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SecondaryTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.hluck.expensetracking.ui.viewmodels.EditViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTopBar(
    appNavController: NavController,
    viewModel: EditViewModel,
    modifier: Modifier = Modifier
) {
    var selectState by remember { mutableStateOf(0) }
    val tabTitles = listOf("支出", "收入")
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                SecondaryTabRow(selectedTabIndex = selectState) {
                    tabTitles.forEachIndexed { index, s ->
                        Tab(
                            selected = selectState == index,
                            onClick = {
                                viewModel.showIncome(index == 1)
                                selectState = index
                            },
                            text = { Text(s) }
                        )
                    }
                }
            }
        },
        navigationIcon = {
            IconButton({
                appNavController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}