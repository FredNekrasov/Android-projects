package com.fred_projects.education.main.model.verification.check_variant

import javax.inject.Inject

class CheckVariant @Inject constructor() : ICheckVariant {
    override fun check(inf: Int?): Int? = if ((inf != null) && (inf > 0)) inf else null
}