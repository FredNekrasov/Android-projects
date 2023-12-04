package com.fred_projects.education.main.use_case.crud

import com.fred_projects.education.main.model.IMainRepository
import com.fred_projects.education.main.model.PracticalWork

class GetPWRecord(private val repository: IMainRepository) {
    suspend operator fun invoke(id: Int): PracticalWork? {
        return repository.getRecord(id)
    }
}