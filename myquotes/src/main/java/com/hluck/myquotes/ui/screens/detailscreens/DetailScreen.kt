package com.hluck.myquotes.ui.screens.detailscreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hluck.myquotes.network.NetworkUIState
import com.hluck.myquotes.ui.components.AppLoadingComponent
import com.hluck.myquotes.ui.components.TopBarComponent
import com.hluck.myquotes.ui.screens.detailscreens.components.DetailQuote

@Composable
fun DetailScreen(
    detailViewModel:DetailViewModel = hiltViewModel<DetailViewModel>(),
    onBack:() -> Unit
) {
    val state = detailViewModel.singleState.collectAsStateWithLifecycle().value
    Scaffold(
        topBar = {
            TopBarComponent(
                showBackButton = true,
                title = "Detail",
                onBackPress = onBack
            )
        }
    ) { paddingValue ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
                .background(MaterialTheme.colorScheme.secondaryContainer),
            contentAlignment = Alignment.Center
        ){
            when(state){
                is NetworkUIState.SUCCESS -> {
                    val quote = state.data
                    DetailQuote(
                        quote = quote
                    )
                }
                is NetworkUIState.LOADING -> {
                    AppLoadingComponent()
                }
                else -> {
                    AppLoadingComponent()
                }
            }

        }
    }
}
