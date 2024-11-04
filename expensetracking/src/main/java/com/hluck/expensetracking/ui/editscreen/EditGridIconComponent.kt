package com.hluck.expensetracking.ui.editscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.Accessibility
import androidx.compose.material.icons.outlined.AlarmOn
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.AvTimer
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Chair
import androidx.compose.material.icons.outlined.ChildCare
import androidx.compose.material.icons.outlined.Construction
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.CreditScore
import androidx.compose.material.icons.outlined.CrueltyFree
import androidx.compose.material.icons.outlined.DirectionsBus
import androidx.compose.material.icons.outlined.Diversity1
import androidx.compose.material.icons.outlined.Diversity3
import androidx.compose.material.icons.outlined.Elderly
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.Flight
import androidx.compose.material.icons.outlined.Grass
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Icecream
import androidx.compose.material.icons.outlined.Key
import androidx.compose.material.icons.outlined.Laptop
import androidx.compose.material.icons.outlined.Liquor
import androidx.compose.material.icons.outlined.LocalFlorist
import androidx.compose.material.icons.outlined.LocalMall
import androidx.compose.material.icons.outlined.LocalShipping
import androidx.compose.material.icons.outlined.Medication
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.MoreHoriz
import androidx.compose.material.icons.outlined.Payments
import androidx.compose.material.icons.outlined.Print
import androidx.compose.material.icons.outlined.Redeem
import androidx.compose.material.icons.outlined.Restaurant
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.Spa
import androidx.compose.material.icons.outlined.SportsEsports
import androidx.compose.material.icons.outlined.SportsTennis
import androidx.compose.material.icons.outlined.WaterDrop
import androidx.compose.material.icons.outlined.WineBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hluck.expensetracking.ui.viewmodels.EditViewModel


val incomeTitles = listOf(
    "工资", "兼职", "理财", "礼金", "报销",
    "奖金", "其他收入"
)

val incomeIcons = listOf(
    Icons.Outlined.CreditCard,
    Icons.Outlined.AlarmOn,
    Icons.Outlined.Savings,
    Icons.Outlined.Redeem,
    Icons.Outlined.Money,
    Icons.Outlined.CreditScore,
    Icons.Outlined.MoreHoriz
)

val expenseTitles = listOf(
    "餐饮", "交通", "养生", "购物",
    "服饰", "娱乐", "红包", "水电",
    "烟酒", "水果", "日用品", "美容",
    "住房", "通讯", "蔬菜", "学习",
    "旅游", "运动", "孩子", "宠物",
    "居家", "书籍", "维修", "医疗",
    "数码", "礼物", "办公", "零食",
    "快递", "社交","长辈","彩票","其他"
)

val expenseIcons = listOf(
    Icons.Outlined.Restaurant,
    Icons.Outlined.DirectionsBus,
    Icons.Outlined.Accessibility,
    Icons.Outlined.ShoppingCart,
    Icons.Outlined.LocalMall,
    Icons.Outlined.SportsEsports,
    Icons.Outlined.Money,
    Icons.Outlined.WaterDrop,
    Icons.Outlined.Liquor,
    Icons.Outlined.AvTimer,
    Icons.Outlined.Key,
    Icons.Outlined.Face,
    Icons.Outlined.Home,
    Icons.Outlined.Call,
    Icons.Outlined.LocalFlorist,
    Icons.Outlined.AutoStories,
    Icons.Outlined.Flight,
    Icons.Outlined.SportsTennis,
    Icons.Outlined.ChildCare,
    Icons.Outlined.CrueltyFree,
    Icons.Outlined.Chair,
    Icons.Outlined.Book,
    Icons.Outlined.Construction,
    Icons.Outlined.Medication,
    Icons.Outlined.CameraAlt,
    Icons.Outlined.Redeem,
    Icons.Outlined.Print,
    Icons.Outlined.Icecream,
    Icons.Outlined.LocalShipping,
    Icons.Outlined.Diversity3,
    Icons.Outlined.Elderly,
    Icons.Outlined.Payments,
    Icons.Outlined.MoreHoriz
)

@Composable
fun EditPaidGridIconComponent(
    viewModel: EditViewModel,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        contentPadding = PaddingValues(6.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(expenseTitles.size) { index ->
            ExpenseItem(
                index,
                viewModel,
                expenseIcons[index],
                expenseTitles[index]
            )
        }
    }
}

@Composable
fun IncomeIconComponent(
    viewModel: EditViewModel,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(5),
        contentPadding = PaddingValues(6.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        items(incomeTitles.size) { index ->
            ExpenseItem(
                index,
                viewModel,
                incomeIcons[index],
                incomeTitles[index]
            )
        }
    }
}


@Composable
fun ExpenseItem(
    index: Int,
    viewModel: EditViewModel,
    icon: ImageVector,
    title: String,
    backgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    val bgColor = if (viewModel.selectedIndex == index) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surface
    }

    val onColor = if (viewModel.selectedIndex == index) {
        MaterialTheme.colorScheme.onPrimary
    } else {
        MaterialTheme.colorScheme.onSurface
    }

    Column(
        modifier = Modifier
            .clickable {
                viewModel.selectIndex(index)
            }
            .padding(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .shadow(3.dp, CircleShape)
                .clip(CircleShape)
                .background(bgColor)
                .padding(6.dp),
            tint = onColor
        )
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            maxLines = 1
        )
    }
}