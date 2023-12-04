package com.fred_projects.education.jumping_rope.use_case.crud.read

import com.fred_projects.education.jumping_rope.model.IJRRepository
import com.fred_projects.education.jumping_rope.model.JRReps
import javax.inject.Inject

class GetJRRecord @Inject constructor(private val repository: IJRRepository) : IGetJRRecord {
    override suspend fun getRecord(id: Int): JRReps? {
        return repository.getRecord(id)
    }
}