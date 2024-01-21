package com.fred_projects.education.jumping_rope.use_case.crud.create_update

import com.fred_projects.education.jumping_rope.model.repository.IJRRepository
import com.fred_projects.education.jumping_rope.model.entity.JRReps
import com.fred_projects.education.jumping_rope.model.verification.check_count.ICheckJRCount
import com.fred_projects.education.jumping_rope.model.verification.check_date.ICheckJRDate
import javax.inject.Inject

class AddJRRecord @Inject constructor(
    private val repository: IJRRepository,
    private val checkCount: ICheckJRCount,
    private val checkDate: ICheckJRDate,
) : IAddJRRecord {
    override suspend fun addRecord(id: Int?, count: Int?, date: String): Int {
        if (checkCount.check(count) == null) return 1
        if (checkDate.check(date) == null) return 2
        val record = JRReps(count!!, date, id)
        repository.insertOrUpdateRecord(record)
        return 0
    }
}