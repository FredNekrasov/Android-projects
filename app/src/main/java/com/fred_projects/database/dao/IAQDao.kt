package com.fred_projects.database.dao

import androidx.room.*
import com.fred_projects.education.rest_api.anime.model.entity.AnimeQuotesEntity

@Dao
interface IAQDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAQ(animeQuote: AnimeQuotesEntity)
    @Query("SELECT * FROM AnimeQuotesEntity WHERE anime = :anime")
    suspend fun getListAQ(anime: String): List<AnimeQuotesEntity>
    @Query("DELETE FROM AnimeQuotesEntity WHERE anime = :title")
    suspend fun deleteAQ(title: String)
}