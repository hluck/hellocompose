package com.hluck.hellocompose.data.di

import android.content.Context
import androidx.room.Room
import com.hluck.hellocompose.data.room.PersonDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun providerDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        PersonDatabase::class.java,
        "my_database"
    )
        .createFromAsset("database/person.db")
        .build()

    @Singleton
    @Provides
    fun providerDao(database: PersonDatabase) = database.personDao()

}