package com.fred_projects.education.jumping_rope.model

import com.fred_projects.database.dao.IJRDao
import kotlinx.coroutines.flow.Flow

class JRRepository(private val dao: IJRDao) : IJRRepository {
    override suspend fun insertOrUpdateRecord(jR: JRReps) {
        dao.insertOrUpdateRecord(jR)
    }
    override suspend fun deleteRecord(jR: JRReps) {
        dao.deleteRecord(jR)
    }
    override fun getData(): Flow<List<JRReps>> {
        return dao.getData()
    }
    override suspend fun getRecord(id: Int): JRReps? {
        return dao.getRecord(id)
    }
}