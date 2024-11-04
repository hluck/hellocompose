package com.hluck.myquotes.ui.screens.homescreens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.hluck.myquotes.common.logd
import com.hluck.myquotes.domain.models.HomeScreenModel
import com.hluck.myquotes.network.NetworkUIState
import com.hluck.myquotes.ui.components.AppLoadingComponent
import com.hluck.myquotes.ui.components.TopBarComponent
import com.hluck.myquotes.ui.screens.homescreens.components.HomeBody

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel<HomeViewModel>(),
    onClick:(id:String) -> Unit
) {
    val state = homeViewModel.quoteFlow.collectAsStateWithLifecycle().value

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopBarComponent(
                title = "Home",
                showBackButton = false
            ) {  }
        }
    ) { paddingValues ->

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            "state: $state".logd()
            when(state){
                is NetworkUIState.LOADING -> {
                    AppLoadingComponent()
                }
                is NetworkUIState.SUCCESS<HomeScreenModel> -> {
                    HomeBody(
                        model = state.data,
                        onClick = { id ->
                            onClick(id)
                        }
                    )
                }
                else -> {
                    AppLoadingComponent()
                }
            }
        }

    }

}
