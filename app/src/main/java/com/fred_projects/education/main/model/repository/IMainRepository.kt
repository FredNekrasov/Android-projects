package com.fred_projects.education.main.model.repository

import com.fred_projects.education.main.model.entity.PracticalWork
import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getData(): Flow<List<PracticalWork>>
    suspend fun deleteRecord(pw: PracticalWork)
    suspend fun insertOrUpdateRecord(pw: PracticalWork)
    suspend fun getRecord(id: Int): PracticalWork?
}