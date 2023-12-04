package com.fred_projects.education.main.model.verification.check_mark

import javax.inject.Inject

class CheckMark @Inject constructor() : ICheckMark {
    override fun check(inf: Int?): Int? = if ((inf != null) && (inf > 0) && (inf <= 5)) inf else null
}