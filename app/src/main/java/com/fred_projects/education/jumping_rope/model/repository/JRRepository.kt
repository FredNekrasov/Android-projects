package com.fred_projects.education.jumping_rope.model.repository

import com.fred_projects.database.dao.IJRDao
import com.fred_projects.education.jumping_rope.model.entity.JRReps
import kotlinx.coroutines.flow.Flow

class JRRepository(private val dao: IJRDao) : IJRRepository {
    override suspend fun insertOrUpdateRecord(jR: JRReps) = dao.insertRecord(jR)
    override suspend fun deleteRecord(jR: JRReps) = dao.deleteRecord(jR)
    override fun getData(): Flow<List<JRReps>> = dao.getData()
    override suspend fun getRecord(id: Int): JRReps? = dao.getRecord(id)
}