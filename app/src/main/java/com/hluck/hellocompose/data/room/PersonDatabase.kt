package com.hluck.hellocompose.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hluck.hellocompose.data.PersonData

@Database(entities = [PersonData::class], version = 1, exportSchema = true)
abstract class PersonDatabase :RoomDatabase(){

    abstract fun personDao(): PersonDao
}