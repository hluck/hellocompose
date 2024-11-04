package com.hluck.roomdemo.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.hluck.roomdemo.data.dao.BookDao
import com.hluck.roomdemo.data.entity.BookEntity
import com.hluck.roomdemo.ui.viewmodels.BookViewmodel
import kotlinx.coroutines.flow.timeout

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    viewmodel: BookViewmodel,
    id:Int,
    navController: NavController
) {
    val book by viewmodel.getBookById(id).collectAsStateWithLifecycle(BookEntity(0, "No Book"))
    var showDeleteDialog by remember { mutableStateOf(false) }
    var showUpdateDialog by remember { mutableStateOf(false) }
    var inputBookName by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Card(
            Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.tertiaryContainer
            ),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = book.title,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    OutlinedButton(
                        {
                            showUpdateDialog = true
                        }
                    ) {
                        Text("修改")
                    }
                    Spacer(modifier = Modifier.width(15.dp))
                    OutlinedButton(
                        {
                            showDeleteDialog = true
                        }
                    ) {
                        Text("删除")
                    }
                }
            }
        }
    }

    if (showDeleteDialog){
        BasicAlertDialog(
            onDismissRequest = {
                showDeleteDialog = false
            }
        ) {
            Card(
                modifier = Modifier.wrapContentSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "确定要删除吗？"
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        OutlinedButton({
                            viewmodel.deleteBook(book)
                            showDeleteDialog = false
                            navController.popBackStack()
                        }) {
                            Text("确定")
                        }
                        OutlinedButton({
                            showDeleteDialog = false
                        }) {
                            Text("取消")
                        }
                    }
                }
            }
        }
    }

    if (showUpdateDialog){
        BasicAlertDialog(
            onDismissRequest = {
                showUpdateDialog = false
            }
        ) {
            Card(
                modifier = Modifier.wrapContentSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "修改书名"
                    )
                    OutlinedTextField(
                        value = inputBookName,
                        onValueChange = {
                            inputBookName = it
                        },
                        label = {
                            Text("请输入书名")
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Row {
                        OutlinedButton({
                            showUpdateDialog = false
                            viewmodel.updateBook(BookEntity(id, inputBookName))
                            navController.popBackStack()
                        }) {
                            Text("确定")

                        }
                        OutlinedButton({
                            showUpdateDialog = false
                        }) {
                            Text("取消")
                        }
                    }
                }
            }
        }
    }

}
