package com.fred_projects.education.main.model.verification.check_image

import javax.inject.Inject

class CheckImage @Inject constructor() : ICheckImage {
    override fun check(inf: String?): String? = if (!inf.isNullOrEmpty() && inf.isNotBlank()) inf else null
}