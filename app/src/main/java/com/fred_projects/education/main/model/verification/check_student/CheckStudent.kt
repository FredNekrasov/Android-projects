package com.fred_projects.education.main.model.verification.check_student

import javax.inject.Inject

class CheckStudent @Inject constructor() : ICheckStudent {
    override fun check(inf: String?): String? = if (!inf.isNullOrEmpty() && inf.isNotBlank()) inf else null
}