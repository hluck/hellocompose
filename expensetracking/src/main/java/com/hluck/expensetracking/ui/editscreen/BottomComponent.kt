package com.hluck.expensetracking.ui.editscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Backspace
import androidx.compose.material.icons.automirrored.outlined.Backspace
import androidx.compose.material.icons.automirrored.outlined.Forward
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hluck.expensetracking.logd
import com.hluck.expensetracking.ui.components.DatePickerModal
import com.hluck.expensetracking.ui.theme.customRed
import com.hluck.expensetracking.ui.viewmodels.EditViewModel

@Composable
fun BottomComponent(
    viewModel: EditViewModel,
    modifier: Modifier = Modifier
) {
    var inputState by remember { mutableStateOf("") }
//    var showDatePicker by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(0.3.dp)
                .padding(horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BasicTextField(
                value = inputState,
                onValueChange = {
                    inputState = it
                },
                modifier = Modifier
                    .padding(top = 10.dp)
                    .weight(1f),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (inputState.isEmpty()) {
                            Text(
                                text = "添加备注~",
                                color = MaterialTheme.colorScheme.onBackground.copy(
                                    0.5f
                                )
                            )
                        }
                        innerTextField()
                    }
                },
                textStyle = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(Color(0xff4776E6), Color(0xff8E54E9))
                    ),
                    fontSize = MaterialTheme.typography.labelLarge.fontSize
                )
            )
            Text(
                text = viewModel.result,
                modifier = Modifier.padding(top = 10.dp),
                fontSize = 23.sp
            )
        }

        Row(
            Modifier.fillMaxWidth()
                .padding(vertical = 3.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .clickable {
                        viewModel.showDatePicker(true)
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = "Date",
                    modifier = Modifier
                        .padding(start = 12.dp),
                    tint = customRed
                )
                Spacer(modifier = Modifier.width(3.dp))
                Text(
                    "今日",
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = "Date",
                modifier = Modifier.padding(start = 12.dp),
                tint = customRed
            )
            Spacer(modifier = Modifier.width(3.dp))
            Text(
                "暴富",
                color = MaterialTheme.colorScheme.primary
            )
        }

        RowComponent(viewModel)
    }

    if (viewModel.showDatePicker){
        DatePickerModal(
            onDateSelected = { date ->
                "date:$date".logd()
            }
        ) {
            viewModel.showDatePicker(false)
        }
    }
}

@Composable
private fun RowComponent(
    viewModel: EditViewModel
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(4)
    ) {
        items(16) { index ->
            when (index) {
                3 -> {
                    Box(
                        Modifier
                            .border(
                                BorderStroke(0.1.dp, MaterialTheme.colorScheme.primary),
                                shape = RectangleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            {
                                viewModel.back()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.Backspace,
                                contentDescription = "Backspace"
                            )
                        }
                    }
                }

                7 -> {
                    Box(
                        Modifier
                            .border(
                                BorderStroke(0.1.dp, MaterialTheme.colorScheme.primary),
                                shape = RectangleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            {
                                viewModel.addResult("-")
                            }
                        ) {
                            Text(
                                "-",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                11 -> {
                    Box(
                        Modifier
                            .border(
                                BorderStroke(0.1.dp, MaterialTheme.colorScheme.primary),
                                shape = RectangleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            {
                                viewModel.addResult("+")
                            }
                        ) {
                            Text(
                                "+",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                12 -> {
                    Box(
                        Modifier
                            .border(
                                BorderStroke(0.1.dp, MaterialTheme.colorScheme.primary),
                                shape = RectangleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            {
                                viewModel.addResult(".")
                            }
                        ) {
                            Text(
                                ".",
                                fontSize = 23.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                14 -> {
                    Box(
                        Modifier
                            .border(
                                BorderStroke(0.1.dp, MaterialTheme.colorScheme.primary),
                                shape = RectangleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            {
                            }
                        ) {
                            Text(
                                "再记",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                15 -> {
                    Box(
                        Modifier
                            .border(
                                BorderStroke(0.1.dp, MaterialTheme.colorScheme.primary),
                                shape = RectangleShape
                            )
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        TextButton(
                            {
                            }
                        ) {
                            Text(
                                "完成",
                                color = MaterialTheme.colorScheme.onPrimary,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                else -> {
                    Box(
                        Modifier
                            .border(
                                BorderStroke(0.1.dp, MaterialTheme.colorScheme.primary),
                                shape = RectangleShape
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        val text = when(index){
                            0 -> "9"
                            1 -> "8"
                            2 -> "7"
                            4 -> "4"
                            5 -> "5"
                            6 -> "6"
                            8 -> "1"
                            9 -> "2"
                            10 -> "3"
                            else -> "0"
                        }
                        TextButton(
                            {
                                viewModel.addResult(text)
                            }
                        ) {
                            Text(
                                text,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}