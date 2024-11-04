package com.hluck.expensetracking.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.FactCheck
import androidx.compose.material.icons.automirrored.outlined.FactCheck
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Equalizer
import androidx.compose.material.icons.filled.FactCheck
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Money
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Equalizer
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.HistoryEdu
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.hluck.expensetracking.ui.navigation.BottomNav
import com.hluck.expensetracking.ui.theme.cuteFont
import com.hluck.expensetracking.ui.viewmodels.MainViewModel

@Composable
fun NavigationBarWithOnlySelectedLabels(
    mainViewModel: MainViewModel,
    bottomNavController: NavController
) {
//    var selectIndex by remember { mutableIntStateOf(0) }
    val labels = listOf("账单", "明细")
    val selectedIcon = listOf(Icons.Filled.CalendarMonth, Icons.Filled.AccountBalanceWallet)
    val unSelectedIcon = listOf(Icons.Outlined.CalendarMonth, Icons.Outlined.AccountBalanceWallet)

    NavigationBar {
        labels.forEachIndexed { index, s ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = if (mainViewModel.selectedBottomIndex == index) selectedIcon[index] else unSelectedIcon[index],
                        contentDescription = "",
                        tint = MaterialTheme.colorScheme.primary
                    )
                },
                onClick = {
                    mainViewModel.toggleBottomIndex(index)
                    when(index){
                        0 -> {
                            mainViewModel.toggleVisibility(true)
                            bottomNavController.navigate(BottomNav.HOME.name)
                        }
                        1 -> {
                            mainViewModel.toggleVisibility(false)
                            bottomNavController.navigate(BottomNav.RANKING.name)
                        }
                    }
                },
                label = {
                    Text(
                        text = s,
                        fontFamily = cuteFont,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                selected = mainViewModel.selectedBottomIndex == index,
                alwaysShowLabel = false
            )
        }
    }

}