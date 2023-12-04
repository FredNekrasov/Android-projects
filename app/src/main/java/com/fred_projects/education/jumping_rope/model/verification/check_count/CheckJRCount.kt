package com.fred_projects.education.jumping_rope.model.verification.check_count

import javax.inject.Inject

class CheckJRCount @Inject constructor() : ICheckJRCount {
    override fun check(inf: Int?): Int? = if ((inf != null) && (inf > 0)) inf else null
}