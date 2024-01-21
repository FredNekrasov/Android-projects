package com.fred_projects.education.main.use_case.crud

import com.fred_projects.education.main.model.repository.IMainRepository
import com.fred_projects.education.main.model.entity.PracticalWork

class GetPWRecord(private val repository: IMainRepository) {
    suspend operator fun invoke(id: Int): PracticalWork? = repository.getRecord(id)
}