package com.fred_projects.education.main.use_case

import com.fred_projects.education.main.use_case.crud.AddPWData
import com.fred_projects.education.main.use_case.crud.DeletePWData
import com.fred_projects.education.main.use_case.crud.GetPWData
import com.fred_projects.education.main.use_case.crud.GetPWRecord

data class MainUseCases(val getData: GetPWData, val deleteData: DeletePWData, val addData: AddPWData, val getPW: GetPWRecord)