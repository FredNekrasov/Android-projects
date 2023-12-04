package com.fred_projects.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fred_projects.education.main.model.PracticalWork
import kotlinx.coroutines.flow.Flow

@Dao
interface IMainDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateRecord(pw: PracticalWork)
    @Delete
    suspend fun deleteRecord(pw: PracticalWork)
    @Query("SELECT * FROM practical_works")
    fun getData(): Flow<List<PracticalWork>>
    @Query("SELECT * FROM practical_works WHERE id = :id")
    suspend fun getRecord(id: Int): PracticalWork?
}