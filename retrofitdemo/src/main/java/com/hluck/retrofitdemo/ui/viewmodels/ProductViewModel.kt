package com.hluck.retrofitdemo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.retrofitdemo.data.Result
import com.hluck.retrofitdemo.data.models.ProductX
import com.hluck.retrofitdemo.data.repository.RepositoryImpl
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: RepositoryImpl
):ViewModel() {

    private val _products = MutableStateFlow<List<ProductX>>(emptyList())
    val products = _products.asStateFlow()

    private val _showErrorToast = Channel<Boolean>()
    val showErrorToast = _showErrorToast.receiveAsFlow()


    init {
        viewModelScope.launch {
            repository.getProductList().collectLatest { result ->
                when(result){
                    is Result.Error -> {
                        _showErrorToast.send(true)
                    }
                    is Result.Success -> {
                        result.data?.let { data ->
                            _products.update { data }
                        }
                    }
                }
            }
        }
    }

}