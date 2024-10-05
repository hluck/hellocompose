package com.hluck.lazylistdemo.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hluck.lazylistdemo.ui.models.Screen

@Composable
fun HomeScreen(
    navHostController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button({
            navHostController.navigate(Screen.LazyRow.route){
                popUpTo(Screen.Home.route){
                    inclusive = false
                }
            }
        }) {
            Text(
                text = "Lazy Row"
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            {
                navHostController.navigate(Screen.LazyColumn.route){
                    popUpTo(Screen.Home.route){
                        inclusive = false
                    }
                }
            }
        ) {
            Text(
                text = "Lazy Column"
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            {
                navHostController.navigate(Screen.LazyGrid.route){
                    popUpTo(Screen.Home.route){
                        inclusive = false
                    }
                }
            }
        ) {
            Text(
                text = "Lazy Grid"
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
//    HomeScreen()
}