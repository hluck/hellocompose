package com.hluck.expensetracking.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EditViewModel:ViewModel() {

    private val _result = mutableStateOf("0")
    val result by _result

    private val _showIncome = mutableStateOf(false)
    val showIncome by _showIncome

    private val _selectedIndex = mutableStateOf(0)
    val selectedIndex by _selectedIndex

    private val _showDatePicker = mutableStateOf(false)
    val showDatePicker by _showDatePicker

    fun showDatePicker(show: Boolean){
        _showDatePicker.value = show
    }

    fun selectIndex(index:Int){
        _selectedIndex.value = index
    }

    fun showIncome(show:Boolean){
        _showIncome.value = show
    }

    fun addResult(text:String){
        if (_result.value == "0") _result.value = ""
        if (("+"==text||"-"==text)){
            if ((('-' == _result.value.lastOrNull()) || ('+' == _result.value.lastOrNull()))){
                _result.value = _result.value.removeRange(_result.value.length-1, _result.value.length)
            } else if (_result.value.contains("+") || _result.value.contains("-")){
                calculateText(_result.value)
            }
        }
        _result.value += text
    }

    fun back(){
        if ("0" == _result.value) return
        if (_result.value.isEmpty() || _result.value.length == 1) {
            _result.value = "0"
        } else {
            _result.value = _result.value.removeRange(_result.value.length-1, _result.value.length)
        }
    }


    private fun calculateText(text: String){
        if (text.contains("-")){
            val (left, right) = text.split("-").map { it.toFloat() }
            _result.value = (left - right).toString()
        } else if (text.contains("+")){
            val (left, right) = text.split("+").map { it.toFloat() }
            _result.value = (left + right).toString()
        }
    }

}