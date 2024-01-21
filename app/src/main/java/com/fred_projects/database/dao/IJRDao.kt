package com.fred_projects.database.dao

import androidx.room.*
import com.fred_projects.education.jumping_rope.model.entity.JRReps
import kotlinx.coroutines.flow.Flow

@Dao
interface IJRDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecord(jR: JRReps)
    @Delete
    suspend fun deleteRecord(jR: JRReps)
    @Query("SELECT * FROM JRReps")
    fun getData(): Flow<List<JRReps>>
    @Query("SELECT * FROM JRReps WHERE id = :id")
    suspend fun getRecord(id: Int): JRReps?

}