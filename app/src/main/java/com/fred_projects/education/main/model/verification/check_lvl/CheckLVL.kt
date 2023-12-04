package com.fred_projects.education.main.model.verification.check_lvl

import javax.inject.Inject

class CheckLVL @Inject constructor() : ICheckLVL {
    override fun check(inf: Int?): Int? = if ((inf != null) && (inf > 0)) inf else null
}