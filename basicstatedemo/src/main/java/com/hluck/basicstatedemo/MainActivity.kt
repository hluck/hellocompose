package com.hluck.basicstatedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hluck.basicstatedemo.ui.theme.HelloComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HelloComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel:WellnessViewModel = viewModel()
                    WaterScreen(viewModel)
                }
            }
        }
    }
}



@Composable
fun WaterScreen(viewModel: WellnessViewModel,modifier: Modifier = Modifier){
    Column {
        WaterCounter(modifier)
//        val list = remember { getWellnessTasks().toMutableStateList() }
        WellnessTaskList(list = viewModel.tasks, onChecked = { task, isChecked ->
            viewModel.changeTaskChecked(task,isChecked)
        }, onClose = { task -> viewModel.remove(task)})
    }
}

@Composable
fun WaterCounter(modifier: Modifier = Modifier) {
    var count by rememberSaveable { mutableIntStateOf(0) }
    var checked by rememberSaveable { mutableStateOf(false) }
    StatelessWater(count,checked,{checked = it},{ count ++ },{ count = 0 },modifier)
}

@Composable
fun WellnessTaskList(
    list: List<WellnessTask>,
    onChecked:(WellnessTask,Boolean) -> Unit,
    onClose: (WellnessTask) -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = modifier
    ) {
        items(list, key = { it.id }){ task ->
            WellnessTaskItem(
                taskName = task.label,
                checked = task.checked,
                onCheckChange = { isChecked -> onChecked(task,isChecked) },
                onClose = {
                    onClose(task)
                }
            )
        }

    }
}

@Composable
private fun StatelessWater(
    count: Int,
    checked: Boolean,
    changeCheck:(Boolean) -> Unit,
    add:() -> Unit,
    clear: (() -> Unit)? = null,
    modifier: Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        if (count > 0) {
            var showTask by remember { mutableStateOf(true) }
            if (showTask){
                WellnessTaskItem("一天8杯水",
                    checked = checked,
                    { changeCheck(it) },
                    { showTask = false }
                )
            }
            Text(
                text = "现在喝了${count}杯水",
                modifier = Modifier.padding(8.dp)
            )
        }
        Row{
            Button(
                onClick = { add() }
            ) {
                Text(
                    "加一杯"
                )
            }
//            Button({ clear() }) {
//                Text("清零")
//            }
        }
    }
}

@Composable
fun WellnessTaskItem(
    taskName:String,
    checked:Boolean,
    onCheckChange:(Boolean) -> Unit,
    onClose:() -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = taskName,
            modifier = Modifier.weight(1f).padding(start = 16.dp)
        )
        Checkbox(
            checked = checked,
            onCheckedChange = onCheckChange
        )
        IconButton(onClick = onClose) {
            Icon(Icons.Filled.Close, contentDescription = null)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WaterCounterPreview() {
    HelloComposeTheme {
        WaterCounter()
    }
}


@Preview(showBackground = true)
@Composable
fun ItemPreview(){
//    WellnessTaskItem(taskName = "Item", onClose = {})
}

fun getWellnessTasks() = List(30){ i -> WellnessTask(i,"Task ## $i")}