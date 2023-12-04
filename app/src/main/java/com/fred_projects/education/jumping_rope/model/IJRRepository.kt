package com.fred_projects.education.jumping_rope.model

import kotlinx.coroutines.flow.Flow

interface IJRRepository {
    suspend fun insertOrUpdateRecord(jR: JRReps)
    suspend fun deleteRecord(jR: JRReps)
    fun getData(): Flow<List<JRReps>>
    suspend fun getRecord(id: Int): JRReps?
}