package com.fred_projects.education.main.model.verification.check_pw

import javax.inject.Inject

class CheckPW @Inject constructor() : ICheckPW {
    override fun check(inf: String?): String? = if (!inf.isNullOrEmpty() && inf.isNotBlank()) inf else null
}