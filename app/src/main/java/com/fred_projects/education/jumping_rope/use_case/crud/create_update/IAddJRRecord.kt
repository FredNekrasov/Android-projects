package com.fred_projects.education.jumping_rope.use_case.crud.create_update

interface IAddJRRecord {
    suspend fun addRecord(id: Int?, count: Int?, date: String): Int
}