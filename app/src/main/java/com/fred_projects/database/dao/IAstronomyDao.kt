package com.fred_projects.database.dao

import androidx.room.*
import com.fred_projects.education.rest_api.astronomy.model.StarInfoEntity
import com.fred_projects.education.rest_api.astronomy.model.service.Converters

@Dao
interface IAstronomyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarInfo(listInfo: List<StarInfoEntity>)
    @Query("SELECT * FROM StarInfoEntity WHERE ra = :ra AND dec = :dec AND radius = :radius")
    suspend fun getStarListInfo(ra: String, dec: String, radius: Float): List<StarInfoEntity>
    @Query("DELETE FROM StarInfoEntity WHERE name IN(:listInfo)")
    suspend fun deleteStarInfo(listInfo: List<String>)
}