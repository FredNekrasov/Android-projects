package com.fred_projects.education.main.use_case

import com.fred_projects.education.main.use_case.crud.*

data class MainUseCases(val getData: GetPWData, val deleteData: DeletePWData, val addData: AddPWData, val getPW: GetPWRecord)