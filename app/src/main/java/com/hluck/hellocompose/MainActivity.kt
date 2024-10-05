package com.hluck.hellocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.hluck.hellocompose.ui.screen.navigation.nav_gragh.SetupNaviGraph
import com.hluck.hellocompose.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    lateinit var nav: NavHostController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            HelloComposeTheme(
                darkTheme = true
            ) {
                nav = rememberNavController()
                SetupNaviGraph(nav)
            }
        }
    }

}

@Composable
fun MyApp(
    modifier: Modifier = Modifier
) {
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }
    if (shouldShowOnboarding) {
        OnboardingScreen(modifier = modifier) {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(name)
    }

}


@Composable
fun CardContent(name: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text("Hello,")
            Text(
                text = name,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
            )
            if (expanded) {
                Text(
                    text = ("Composem ipsum color sit lazy, " +
                            "padding theme elit, sed do bouncy. ").repeat(4)
                )
            }
        }
        IconButton({ expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}

@Composable
fun Greetings(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000) { "$it" }
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(names) { name ->
            Greeting(name)
        }
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome to the basics Codelab!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = { onContinueClicked() }
        ) {
            Text("Continue")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    //使用remember保存状态,防止重新创建
    HelloComposeTheme {
        Column {
            var names = remember { mutableStateListOf("name","hello") }
            val upperCaseNames by remember { derivedStateOf { names.map { it.uppercase() } } }
//        val upperCaseNames = remember(names) { names.map { it.uppercase() }
            for (name in upperCaseNames) {
                Text(name, modifier = Modifier.clickable { names.add("day") })
            }
        }
//        Text(upperCaseName, modifier = Modifier.clickable { name = "have a good day." })
    }
}

@Composable
fun RequestUrl() {
    var url = "Hello"
    Text("result:$url")
}