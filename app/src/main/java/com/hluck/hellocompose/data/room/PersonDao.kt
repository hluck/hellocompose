package com.hluck.hellocompose.data.room

import androidx.room.Dao
import androidx.room.Query
import com.hluck.hellocompose.data.Person
import com.hluck.hellocompose.data.PersonData
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDao {

    @Query("SELECT * FROM person")
    fun readAll():Flow<List<PersonData>>
}