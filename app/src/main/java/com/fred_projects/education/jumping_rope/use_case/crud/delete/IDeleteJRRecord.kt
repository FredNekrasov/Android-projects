package com.fred_projects.education.jumping_rope.use_case.crud.delete

import com.fred_projects.education.jumping_rope.model.JRReps

interface IDeleteJRRecord {
    suspend fun deleteRecord(jRReps: JRReps)
}