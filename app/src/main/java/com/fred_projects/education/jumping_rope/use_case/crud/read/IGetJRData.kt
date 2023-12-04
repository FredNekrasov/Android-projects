package com.fred_projects.education.jumping_rope.use_case.crud.read

import com.fred_projects.education.jumping_rope.model.JRReps
import kotlinx.coroutines.flow.Flow

interface IGetJRData {
    fun getData(): Flow<List<JRReps>>
}