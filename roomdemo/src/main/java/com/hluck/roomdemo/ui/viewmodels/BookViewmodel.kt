package com.hluck.roomdemo.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hluck.roomdemo.data.entity.BookEntity
import com.hluck.roomdemo.data.repository.BookRepository
import kotlinx.coroutines.launch

class BookViewmodel(
    private val repository: BookRepository
):ViewModel() {

    fun addBook(bookEntity: BookEntity){
        viewModelScope.launch {
            repository.addBook(bookEntity)
        }
    }

    fun deleteBook(book:BookEntity){
        viewModelScope.launch {
            repository.deleteBook(book)
        }
    }

    fun updateBook(book:BookEntity){
        viewModelScope.launch {
            repository.updateBook(book)
        }
    }

    fun getBookById(id:Int) = repository.getBookById(id)

    fun getAllBooks() = repository.getAllBooks()
}