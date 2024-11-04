package com.hluck.roomdemo.data.repository

import com.hluck.roomdemo.data.dao.BookDao
import com.hluck.roomdemo.data.db.BookDB
import com.hluck.roomdemo.data.entity.BookEntity

class BookRepository(
    private val bookDB: BookDB
) {

    suspend fun addBook(bookEntity: BookEntity){
        bookDB.bookDao().addBook(bookEntity)
    }

    fun getAllBooks() = bookDB.bookDao().getAllBooks()

    fun getBookById(id:Int) = bookDB.bookDao().getBookById(id)

    suspend fun deleteBook(book:BookEntity) = bookDB.bookDao().deleteBook(book)

    suspend fun updateBook(book:BookEntity) = bookDB.bookDao().updateBook(book)

}