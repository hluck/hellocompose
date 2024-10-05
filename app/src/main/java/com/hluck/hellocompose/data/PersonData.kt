package com.hluck.hellocompose.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person")
data class PersonData(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val age:Int
)
