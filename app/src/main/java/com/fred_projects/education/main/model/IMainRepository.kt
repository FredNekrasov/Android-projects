package com.fred_projects.education.main.model

import kotlinx.coroutines.flow.Flow

interface IMainRepository {
    fun getData(): Flow<List<PracticalWork>>
    suspend fun deleteRecord(pw: PracticalWork)
    suspend fun insertOrUpdateRecord(pw: PracticalWork)
    suspend fun getRecord(id: Int): PracticalWork?
}