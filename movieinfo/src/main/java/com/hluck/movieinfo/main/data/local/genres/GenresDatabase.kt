package com.hluck.movieinfo.main.data.local.genres

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [GenreEntity::class],
    version = 1,
    exportSchema = false
)
abstract class GenresDatabase:RoomDatabase() {

    abstract val genreDao:GenreDao
}