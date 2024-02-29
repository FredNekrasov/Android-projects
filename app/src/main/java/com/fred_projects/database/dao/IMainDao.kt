package com.fred_projects.database.dao

import androidx.room.*
import com.fred_projects.education.main.model.entity.PracticalWork
import kotlinx.coroutines.flow.Flow

@Dao
interface IMainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(pw: PracticalWork)
    @Delete
    suspend fun deleteRecord(pw: PracticalWork)
    @Query("SELECT * FROM practical_works")
    fun getData(): Flow<List<PracticalWork>>
    @Query("SELECT * FROM practical_works WHERE id = :id")
    suspend fun getRecord(id: Int): PracticalWork?
}