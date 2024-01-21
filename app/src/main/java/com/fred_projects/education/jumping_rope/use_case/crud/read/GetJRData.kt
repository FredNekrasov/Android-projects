package com.fred_projects.education.jumping_rope.use_case.crud.read

import com.fred_projects.education.jumping_rope.model.repository.IJRRepository
import com.fred_projects.education.jumping_rope.model.entity.JRReps
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetJRData @Inject constructor(private val repository: IJRRepository) : IGetJRData {
    override fun getData(): Flow<List<JRReps>> = repository.getData()
}