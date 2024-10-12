package com.hluck.movieinfo.main.data.local.media

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaList(
        mediaList: List<MediaEntity>
    )

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMediaItem(
        mediaEntity: MediaEntity
    )

    @Update
    suspend fun updateMediaItem(
        mediaItem: MediaEntity
    )


    @Query(
        """
            DELETE FROM MediaEntity 
            WHERE mediaType = :mediaType AND category = :category
        """
    )
    suspend fun deleteMediaByTypeAndCategory(
        mediaType: String, category: String
    )

    @Query(
        """
            DELETE FROM MediaEntity 
            WHERE category = :category
        """
    )
    suspend fun deleteTrendingMediaList(
        category: String
    )

    @Query(
        """
            SELECT * FROM MediaEntity 
            WHERE mediaType = :mediaType AND category = :category
        """
    )
    suspend fun getMediaListByTypeAndCategory(
        mediaType: String, category: String
    ): List<MediaEntity>

    @Query(
        "SELECT * FROM mediaentity WHERE id = :id"
    )
    suspend fun selectMediaByID(id: Int): MediaEntity

    @Query(
        "SELECT * FROM mediaentity WHERE category = :category"
    )
    suspend fun getTrendingMediaList(category: String): List<MediaEntity>

    @Query(
        "SELECT * FROM mediaentity WHERE mediaType = :type AND category = :category"
    )
    suspend fun selectMediasByTypeAndCategory(type: String, category: String): List<MediaEntity>

}