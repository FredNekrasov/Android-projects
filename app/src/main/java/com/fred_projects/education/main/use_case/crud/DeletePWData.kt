package com.fred_projects.education.main.use_case.crud

import com.fred_projects.education.main.model.repository.IMainRepository
import com.fred_projects.education.main.model.entity.PracticalWork

class DeletePWData(private val repository: IMainRepository) {
    suspend operator fun invoke(pw: PracticalWork) {
        repository.deleteRecord(pw)
    }
}