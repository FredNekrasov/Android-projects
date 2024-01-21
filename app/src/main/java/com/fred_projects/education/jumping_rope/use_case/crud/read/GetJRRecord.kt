package com.fred_projects.education.jumping_rope.use_case.crud.read

import com.fred_projects.education.jumping_rope.model.repository.IJRRepository
import com.fred_projects.education.jumping_rope.model.entity.JRReps
import javax.inject.Inject

class GetJRRecord @Inject constructor(private val repository: IJRRepository) : IGetJRRecord {
    override suspend fun getRecord(id: Int): JRReps? = repository.getRecord(id)
}