package com.fred_projects.education.jumping_rope.use_case.crud.delete

import com.fred_projects.education.jumping_rope.model.repository.IJRRepository
import com.fred_projects.education.jumping_rope.model.entity.JRReps
import javax.inject.Inject

class DeleteJRRecord @Inject constructor(private val repository: IJRRepository) : IDeleteJRRecord {
    override suspend fun deleteRecord(jRReps: JRReps) = repository.deleteRecord(jRReps)
}