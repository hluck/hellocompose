package com.hluck.roomdemo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.hluck.roomdemo.data.dao.BookDao
import com.hluck.roomdemo.data.entity.BookEntity

@Database(entities = [BookEntity::class], version = 1, exportSchema = false)
abstract class BookDB : RoomDatabase() {

    abstract fun bookDao(): BookDao

    companion object {
        @Volatile
        private var instance: BookDB? = null

        fun getInstance(context: Context): BookDB {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    BookDB::class.java,
                    "book.db"
                ).build()
            }
        }
    }
}