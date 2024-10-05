package com.hluck.hellocompose.data

import com.hluck.hellocompose.data.room.PersonDao
import javax.inject.Inject

class PersonRepository @Inject constructor(
    private val personDao: PersonDao
) {
    val allPerson = personDao.readAll()
}