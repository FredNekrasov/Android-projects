package com.fred_projects.education.jumping_rope.use_case

import com.fred_projects.education.jumping_rope.use_case.crud.create_update.IAddJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.delete.IDeleteJRRecord
import com.fred_projects.education.jumping_rope.use_case.crud.read.*

data class JRUseCases(
    val addJRRecord: IAddJRRecord,
    val deleteJRRecord: IDeleteJRRecord,
    val getJRRecord: IGetJRRecord,
    val getJRData: IGetJRData,
)