package com.hluck.expensetracking.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {

    // 定义一个可变状态 isVisible 来控制 FAB 的显示/隐藏
    private val _isVisible = mutableStateOf(true)
    val isVisible: Boolean by _isVisible

    private val _selectIndex = mutableIntStateOf(0)
    val selectedBottomIndex by _selectIndex

    fun toggleVisibility(visible:Boolean) {
        _isVisible.value = visible
    }

    fun toggleBottomIndex(index:Int) {
        _selectIndex.value = index
    }
}