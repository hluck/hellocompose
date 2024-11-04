package com.hluck.roomdemo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hluck.roomdemo.data.entity.BookEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {

    @Insert
    suspend fun addBook(bookEntity: BookEntity)

    @Query("SELECT * FROM bookentity")
    fun getAllBooks():Flow<List<BookEntity>>

    @Query("SELECT * FROM bookentity WHERE id = :id")
    fun getBookById(id:Int):Flow<BookEntity>

    @Delete
    suspend fun deleteBook(book:BookEntity)

    @Update
    suspend fun updateBook(book:BookEntity)
}