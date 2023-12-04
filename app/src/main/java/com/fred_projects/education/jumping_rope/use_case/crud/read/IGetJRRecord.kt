package com.fred_projects.education.jumping_rope.use_case.crud.read

import com.fred_projects.education.jumping_rope.model.JRReps

interface IGetJRRecord {
    suspend fun getRecord(id: Int): JRReps?
}